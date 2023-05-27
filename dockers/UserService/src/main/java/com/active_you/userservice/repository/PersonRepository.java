package com.active_you.userservice.repository;

import com.active_you.userservice.models.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByName(String name);

    @NonNull
    Optional<Person> findById(@NonNull Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT w.*, pw.init_date, pw.end_date, pw.completed " +
            "FROM person_workout pw " +
            "JOIN workout w ON pw.id_workout = w.id " +
            "WHERE pw.id_person = :personId", nativeQuery = true)
    List<Object[]> getPersonalWorkouts(Long personId);
}