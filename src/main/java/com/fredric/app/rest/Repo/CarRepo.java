package com.fredric.app.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fredric.app.rest.Entities.Car;

public interface CarRepo extends JpaRepository<Car, Long> {

}
