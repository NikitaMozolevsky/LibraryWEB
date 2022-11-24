package com.nikita.al_fp.util;

import com.nikita.al_fp.dao.BookDao;
import com.nikita.al_fp.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private BookDao bookDao;

    @Autowired
    public BookValidator(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (bookDao.selectBookNameByBookName(book.getName()).isPresent()) {
            errors.rejectValue("name", "", "Book with this name is already taken");
        }
    }
}
