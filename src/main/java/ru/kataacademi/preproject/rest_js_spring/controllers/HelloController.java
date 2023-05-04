package ru.kataacademi.preproject.rest_js_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {

    @GetMapping("/user")
    public String showUser() {
        return "user";
    }

    @GetMapping("/admin")
    public  String showAdminPage() {return "admin_page";}
}
