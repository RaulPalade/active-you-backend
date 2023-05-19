package com.active_you.userservice.repository;

import com.active_you.userservice.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByName(String name);
    Person findById(Integer id);
}
