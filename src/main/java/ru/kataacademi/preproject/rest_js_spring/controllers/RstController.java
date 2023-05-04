package ru.kataacademi.preproject.rest_js_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kataacademi.preproject.rest_js_spring.models.Person;
import ru.kataacademi.preproject.rest_js_spring.models.Role;
import ru.kataacademi.preproject.rest_js_spring.services.PersonService;
import ru.kataacademi.preproject.rest_js_spring.services.RoleService;
import ru.kataacademi.preproject.rest_js_spring.util.PersonErrorResponce;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class RstController {

    private final PersonService personService;
    private final RoleService roleService;

    @Autowired
    public RstController(PersonService peopleService, RoleService roleService) {
        this.personService = peopleService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(personService.listPerson(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleService.listRole(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") long id) {
        return personService.get(id);
    }

    @GetMapping("/auth")
    public ResponseEntity<Person> getAuthPerson(@AuthenticationPrincipal Person person) {
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@RequestBody Person person, @PathVariable("id") Long id) {
        personService.update(id, person,
                person.getRoles().isEmpty() ? Arrays.asList() : person.getRoles().stream().map(r -> r.getId()).toList());
        return new ResponseEntity<>(personService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody @Valid Person person) {
        System.err.println("save");
        List<Long> lst = new ArrayList<>();
        lst.add(1L);
        personService.add(person,
                person.getRoles().isEmpty() ? lst : person.getRoles().stream().map(r -> r.getId()).toList());
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<PersonErrorResponce> handleException() {
        PersonErrorResponce responce = new PersonErrorResponce(
                "Person with this id was nit found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responce, HttpStatus.NOT_FOUND);
    }
}
