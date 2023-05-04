package ru.kataacademi.preproject.rest_js_spring.services;

import ru.kataacademi.preproject.rest_js_spring.models.Person ;

import java.util.List;


public interface PersonService {
    public List<Person> listPerson();
    public void add(Person person, List<Long> roles);
    public void update(long id, Person updatedPerson, List<Long> roles);
    public void delete(long id);
    public Person get(long id);
}
