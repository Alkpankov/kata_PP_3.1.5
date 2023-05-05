package ru.kataacademi.preproject.rest_js_spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kataacademi.preproject.rest_js_spring.util.PersonErrorResponce;
import ru.kataacademi.preproject.rest_js_spring.util.PersonNotFoundException;

@ControllerAdvice
public class ExсeptionHandlerController {
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponce> handleException(PersonNotFoundException e) {
        PersonErrorResponce responce = new PersonErrorResponce(
                "Person with this id was nit found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responce, HttpStatus.NOT_FOUND);
    }
}
