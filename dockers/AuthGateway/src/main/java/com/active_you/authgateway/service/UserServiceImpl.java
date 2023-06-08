package com.active_you.authgateway.service;

import com.active_you.authgateway.models.Person;
import com.active_you.authgateway.models.PersonRoleWrapper;
import com.active_you.authgateway.models.Role;
import com.active_you.authgateway.repository.PersonRepository;
import com.active_you.authgateway.repository.RoleRepository;
import com.active_you.authgateway.security.MyPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(PersonRepository personRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException("Utente non registrato");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        person.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new MyPerson(person.getId(), person.getName(), person.getEmail(), person.getPassword(), authorities);
    }

    public Long saveUser(PersonRoleWrapper personRoleWrapper) {
        Optional<Role> role = roleRepository.findByName(personRoleWrapper.getRole().getName());
        Role newRole = new Role();
        role.ifPresent(value -> newRole.setId(value.getId()));

        Person person = personRoleWrapper.getPerson();
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        person.setRoles(new ArrayList<>());
        person.getRoles().add(newRole);

        return personRepository.save(person).getId();
    }

    public void removeUserById(Long id) {
        personRepository.deleteById(id);
    }
}
