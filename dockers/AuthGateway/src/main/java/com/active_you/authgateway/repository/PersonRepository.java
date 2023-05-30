package com.active_you.authgateway.repository;

import com.active_you.authgateway.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @NonNull
    Optional<Person> findById(@NonNull Long id);

    Person findByEmail(String email);
}