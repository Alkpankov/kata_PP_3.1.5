package ru.kataacademi.preproject.rest_js_spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kataacademi.preproject.rest_js_spring.models.Person;

@RestController
public class PrincipalRestController {
    @GetMapping("/auth")
    public ResponseEntity<Person> getAuthPerson(@AuthenticationPrincipal Person person) {
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
