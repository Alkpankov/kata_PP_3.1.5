package ru.kataacademi.preproject.rest_js_spring.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kataacademi.preproject.rest_js_spring.models.Role;
import ru.kataacademi.preproject.rest_js_spring.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/roles")
public class RolesRestController {

    private final RoleService roleService;

    @Autowired
    public RolesRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(roleService.listRole());
    }
}
