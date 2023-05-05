package ru.kataacademi.preproject.rest_js_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataacademi.preproject.rest_js_spring.models.Person;
import ru.kataacademi.preproject.rest_js_spring.models.Role;
import ru.kataacademi.preproject.rest_js_spring.repositories.PersonRepository;
import ru.kataacademi.preproject.rest_js_spring.util.PersonNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<Person> listPerson() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public void add(Person person, List<Long> roles) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(setRoleByListId(person, roles));
    }

    private Person setRoleByListId(Person person, List<Long> roleId){
        Set<Role> role = new HashSet<>(roleService.findAllById(roleId));
        if(role.isEmpty()) {
            person.setRoles(roleService.findById(0L));
        } else {
            person.setRoles(role);
        }
        return person;
    }

    @Transactional
    @Override
    public void update(long id, Person updatedPerson, List<Long> roles) {

        if (updatedPerson.getPassword().isEmpty()){
            updatedPerson
                    .setPassword(get(id).getPassword());
        } else {
            updatedPerson.setPassword(passwordEncoder.encode(updatedPerson.getPassword()));
        }

        if (updatedPerson.getRoles().isEmpty()) {
            updatedPerson.setRoles(get(id).getRoles());
        }

        updatedPerson.setId(id);
        personRepository.save(setRoleByListId(updatedPerson, roles));
    }

    @Transactional
    @Override
    public void delete(long id) {
        personRepository.deleteById(id);
    }
    @Override
    public Person get(long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(PersonNotFoundException::new);
    }

}