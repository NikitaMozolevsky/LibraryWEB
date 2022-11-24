package com.nikita.al_fp.controllers;

import com.nikita.al_fp.dao.BookDao;
import com.nikita.al_fp.dao.PersonDao;
import com.nikita.al_fp.entity.Person;
import com.nikita.al_fp.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
@PropertySource("classpath:database.properties")
public class PeopleController {

    private final PersonDao personDao;
    private final PersonValidator personValidator;
    public static final String PEOPLE_REDIRECT_PAGE = "redirect:/people";

    @Autowired
    public PeopleController(PersonDao personDao, PersonValidator personValidator, BookDao bookDao) {
        this.personDao = personDao;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String selectPerson(Model model) {
        model.addAttribute("people", personDao.selectPeople());
        return "people/select_person";
    }

    @GetMapping("/new")
    public String insertIntoPersonPage(@ModelAttribute("person") Person person) {
        return "people/insert_into_person_page";
    }

    @GetMapping("/{id}")
    public String selectPersonById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.selectPerson(id).get());
        model.addAttribute("books", personDao.selectBookByPersonId(id).get());
        return "people/select_person_by_id";
    }

    @GetMapping("/{id}/edit")
    public String updatePersonPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.selectPerson(id).get());
        return "people/update_person_page";
    }

    @PostMapping()
    public String insertIntoPersonAct(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()) {
            return "people/insert_into_person_page";
        }
        personDao.insertPerson(person);
        return PEOPLE_REDIRECT_PAGE;
    }

    @PatchMapping("/{id}")
    public String updatePersonAct(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                                  @PathVariable int id) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println(this.getClass() + "Error");
            return "people/update_person_page";
        }
        personDao.updatePerson(id, person);
        return PEOPLE_REDIRECT_PAGE;
    }

    @DeleteMapping("{id}")
    public String deletePerson(@PathVariable int id) {
        personDao.deletePerson(id);
        return PEOPLE_REDIRECT_PAGE;
    }
}
