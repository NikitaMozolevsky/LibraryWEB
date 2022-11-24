package com.nikita.al_fp.controllers;

import com.nikita.al_fp.dao.BookDao;
import com.nikita.al_fp.dao.PersonDao;
import com.nikita.al_fp.entity.Book;
import com.nikita.al_fp.entity.Person;
import com.nikita.al_fp.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/book")
@PropertySource("classpath:database.properties")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;
    private final BookValidator bookValidator;
    public static final String BOOK_REDIRECT_PAGE = "redirect:/book";

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao, BookValidator bookValidator) {
        this.bookDao = bookDao;
        this.personDao = personDao;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public String selectBook(Model model) {
        model.addAttribute("books", bookDao.selectBook());
        return "book/select_book";
    }

    @GetMapping("/new")
    public String insertIntoBookPage(@ModelAttribute("book") Book book) {
        return "book/insert_into_book_page";
    }

    @GetMapping("/{id}")
    public String selectBookById(@PathVariable("id") int id,
                                 @ModelAttribute("person") Person person,
                                 @ModelAttribute("new_book") Book book,
                                 Model model) {
        model.addAttribute("book", bookDao.selectBookById(id).get());
        model.addAttribute("owner", bookDao.selectPersonByBookId(id));
        model.addAttribute("owner_name", personDao.selectPersonNameByBookId(String.valueOf(id)));
        model.addAttribute("people", personDao.selectPeople());
        return "book/select_book_by_id";
    }

    @GetMapping("/{id}/edit")
    public String updateBookPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.selectBookById(id).get());
        return "book/update_book_page";
    }

    @PostMapping()
    public String insertIntoBookAct(@ModelAttribute("book") @Valid Book book, // TODO: 11/15/2022
                                      BindingResult bindingResult) {

        bookValidator.validate(book, bindingResult);

        if(bindingResult.hasErrors()) {
            return "book/insert_into_book_page";
        }
        bookDao.insertBook(book);
        return BOOK_REDIRECT_PAGE;
    }

    @PatchMapping("/{id}")
    public String updatePersonAct(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                                  @PathVariable int id) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println(this.getClass() + "Error");
            return "book/update_book_page";
        }
        bookDao.updateBook(id, book);
        return BOOK_REDIRECT_PAGE;
    }

    @DeleteMapping("{id}")
    public String deleteBook(@PathVariable int id) {
        bookDao.deleteBook(id);
        return BOOK_REDIRECT_PAGE;
    }
    @DeleteMapping("{id}/person")
    public String deletePersonFromBook(@PathVariable int id) {
        bookDao.deletePersonFromBook(id);
        return BOOK_REDIRECT_PAGE;
    }

    @PatchMapping("set/owner")
    public String setBookOwner(Model model,
                               @ModelAttribute("new_book") Book book) {
        bookDao.assignBookOwner(book.getPersonId(), book.getId());
        return BOOK_REDIRECT_PAGE;
    }
}
