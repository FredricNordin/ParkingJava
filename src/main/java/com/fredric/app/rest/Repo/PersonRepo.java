package com.fredric.app.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fredric.app.rest.Entities.Person;

public interface PersonRepo extends JpaRepository<Person, Long> {
}
