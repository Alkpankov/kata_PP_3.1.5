package ru.kataacademi.preproject.rest_js_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataacademi.preproject.rest_js_spring.models.Role;
import ru.kataacademi.preproject.rest_js_spring.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> listRole() {
        return roleRepository.findAll();
    }
    @Override
    public List<Role> findAllById(List<Long> roles) {
        return roleRepository.findAllById(roles);
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }
}