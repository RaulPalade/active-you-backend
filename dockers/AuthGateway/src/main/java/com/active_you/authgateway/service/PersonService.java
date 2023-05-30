package com.active_you.authgateway.service;

import com.active_you.authgateway.models.Person;
import com.active_you.authgateway.repository.PersonRepository;
import com.active_you.authgateway.security.MyPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Transactional
@Component
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException("Utente non registrato");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(person.getRole()));

        return new MyPerson(person.getId(), person.getName(), person.getEmail(), person.getPassword(), authorities);
    }

    public void saveUser(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }
}
