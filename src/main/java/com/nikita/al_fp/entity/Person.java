package com.nikita.al_fp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {

    private int id;

    @Pattern(regexp = "[A-Z]\\w{2,50}, [A-Z]\\w{2,50}", message = "name should be in this format:Name, Lastname")
    private String name;

    /*@Pattern(regexp = "^[12][0-9]{3}$", message = "year of birth should be in this borders: 1000-2999")*/
    @Min(value = 1899, message = "year of birth should be greater than 1900")
    @Max(value = 2899, message = "year of birth should be less than 2900")
    private int birthYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (birthYear != person.birthYear) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + birthYear;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", birthYear=").append(birthYear);
        sb.append('}');
        return sb.toString();
    }
}
