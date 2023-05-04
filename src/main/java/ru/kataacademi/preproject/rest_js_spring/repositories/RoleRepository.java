package ru.kataacademi.preproject.rest_js_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataacademi.preproject.rest_js_spring.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
