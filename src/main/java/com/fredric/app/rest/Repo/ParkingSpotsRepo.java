package com.fredric.app.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fredric.app.rest.Entities.ParkingSpots;

public interface ParkingSpotsRepo extends JpaRepository<ParkingSpots, Long>{
    
}
