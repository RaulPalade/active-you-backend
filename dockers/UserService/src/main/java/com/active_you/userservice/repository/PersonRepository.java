package com.active_you.userservice.repository;

import com.active_you.userservice.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByName(String name);

    @NonNull
    Optional<Person> findById(@NonNull Long id);
}