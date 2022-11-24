package com.nikita.al_fp.util;

import com.nikita.al_fp.dao.impl.PersonDaoImpl;
import com.nikita.al_fp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDaoImpl personDao;

    @Autowired
    public PersonValidator(PersonDaoImpl personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) { /**Check if this param is non-unique*/
        Person person = (Person) target;

        if (personDao.selectPersonNameByPersonName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Person with this name is already taken");
        }

        /**is there a person with the same name in the DB*/
    }
}
