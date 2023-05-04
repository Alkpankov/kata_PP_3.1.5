package ru.kataacademi.preproject.rest_js_spring.services;

import ru.kataacademi.preproject.rest_js_spring.models.Role;

import java.util.List;

public interface RoleService {
    public List<Role> listRole();
    public List<Role> findAllById(List<Long> roles);
    public Role findById(Long id);
}
