package com.active_you.userservice.repository;

import com.active_you.userservice.models.Person;
import com.active_you.userservice.models.PersonWorkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @NonNull
    Optional<Person> findById(@NonNull Long id);

    @Query("SELECT pw FROM PersonWorkout pw JOIN pw.workout w WHERE pw.person.id = :personId")
    Set<PersonWorkout> getPersonalWorkouts(Long personId);
}