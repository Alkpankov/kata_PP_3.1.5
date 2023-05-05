package ru.kataacademi.preproject.rest_js_spring.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kataacademi.preproject.rest_js_spring.models.Person;
import ru.kataacademi.preproject.rest_js_spring.services.PersonService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/admin/users")
public class PersonRestController {

    private final PersonService personService;

    @Autowired
    public PersonRestController(PersonService peopleService) {
        this.personService = peopleService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok(personService.listPerson());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
        return ResponseEntity.ok(personService.get(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@RequestBody Person person, @PathVariable("id") Long id) {
        personService.update(id, person,
                person.getRoles().isEmpty() ? Arrays.asList() : person.getRoles().stream().map(r -> r.getId()).toList());
        return ResponseEntity.ok(personService.get(id));
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody @Valid Person person) {
        System.err.println("save");
        List<Long> lst = new ArrayList<>();
        lst.add(1L);
        personService.add(person,
                person.getRoles().isEmpty() ? lst : person.getRoles().stream().map(r -> r.getId()).toList());
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }
}
