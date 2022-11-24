package com.nikita.al_fp.controllers;

import com.nikita.al_fp.dao.BookDao;
import com.nikita.al_fp.dao.PersonDao;
import com.nikita.al_fp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
@PropertySource("classpath:database.properties")
public class StartController {

    private final PersonDao personDao;
    private final BookDao bookDao;

    @Autowired
    public StartController(PersonDao personDao, BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String initProgramWithData(Model model) {
        personDao.startProgramProcessPerson();
        bookDao.startProgramProcessBook();
        model.addAttribute("people", personDao.selectPeople());
        return "people/select_person";
    }
}
