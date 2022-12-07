package com.fredric.app.rest.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fredric.app.rest.Entities.Car;
import com.fredric.app.rest.Entities.ParkingEvent;

public interface ParkingEventRepo extends JpaRepository<ParkingEvent, Long>{

    // To find a car in the ParkingEvent table..
    ParkingEvent findByCar(Car car);

    // To find all active parkings in the ParkingEvent table..
    List<ParkingEvent> findByisActive(boolean isActive);
    
}
