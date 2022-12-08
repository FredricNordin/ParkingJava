package com.fredric.app.rest.Controller;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fredric.app.rest.Entities.Car;
import com.fredric.app.rest.Entities.ParkingEvent;
import com.fredric.app.rest.Entities.ParkingSpots;
import com.fredric.app.rest.Entities.Person;
import com.fredric.app.rest.Repo.CarRepo;
import com.fredric.app.rest.Repo.ParkingEventRepo;
import com.fredric.app.rest.Repo.ParkingSpotsRepo;
import com.fredric.app.rest.Repo.PersonRepo;

@RestController
@RequestMapping("/api")
public class ApiControllers {
    @Autowired PersonRepo personRepo;
    @Autowired CarRepo carRepo;
    @Autowired ParkingSpotsRepo parkingSpotsRepo;
    @Autowired ParkingEventRepo parkingEventRepo;


    @GetMapping("/")
    public String getPage() {
        return "Hello World, welcome to Freddes Mega Parking System..!";
    }

    // Get all persons.
    @GetMapping("/person")
    public List<Person> getPerson() {
        return personRepo.findAll();
    }

    // Add a person.
    @PostMapping("/person")
    public String addPerson(@RequestBody Person person) {
        personRepo.save(person);
        return "Saved person..!";
    }

    // Get a person by id.
    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personRepo.findById(id).get();
    }

    // Add a car to person.
    @PostMapping("/person/{id}/car")
    public String addCar(@PathVariable Long id, @RequestBody Car car) {
        Person person = personRepo.findById(id).get();
        car.setPerson(person);
        carRepo.save(car);
        return "Saved car to person..!";
    }

    // Get cars by person id.
    @GetMapping("/person/{id}/car")
    public List<Car> getCarsByPersonId(@PathVariable Long id) {
        Person person = personRepo.findById(id).get();
        return person.getCars();
    }

    // Add a parking spot.
    @PostMapping("/parkingspot")
    public String addParkingSpot(@RequestBody ParkingSpots parkingSpot) {
        parkingSpotsRepo.save(parkingSpot);
        return "Saved parking spot..!";
    }

    // Get all parking spots.
    @GetMapping("/parkingspot")
    public List<ParkingSpots> getParkingSpots() {
        return parkingSpotsRepo.findAll();
    }

    // Park event /park/{poersonId}/{carId}/{parkingSpotId}. I don't understand spatial data so I go with parkingspots instead :-).
    @PostMapping("/park/{personId}/{carId}/{parkingSpotId}")
    public ParkingEvent parkCar(@PathVariable long personId, @PathVariable long carId, @PathVariable long parkingSpotId) {
        Optional<Person> personOptional = personRepo.findById(personId);
        Optional<Car> carOptional = carRepo.findById(carId);
        Optional<ParkingSpots> parkingSpotOptional = parkingSpotsRepo.findById(parkingSpotId);

        // If any of the above is not present throw exception. isPresent() returns false if no found..
        if (!personOptional.isPresent() || !carOptional.isPresent() || !parkingSpotOptional.isPresent()) {
            throw new IllegalArgumentException("person, car or parking spot not found dude..!?");
        }

        Person person = personOptional.get();
        Car car = carOptional.get();
        ParkingSpots parkingSpot = parkingSpotOptional.get();

        ParkingEvent parkingEvent = new ParkingEvent();
        parkingEvent.setStartTime(Instant.now());
        // Park endTime is 5 minutes from now.
        parkingEvent.setEndTime(Instant.now().plusSeconds(300));
        parkingEvent.setPerson(person);
        parkingEvent.setCar(car);
        parkingEvent.setParkingSpot(parkingSpot);
        parkingEvent.setActive(true);

        return parkingEventRepo.save(parkingEvent);
    }

    // Get all parking events that are active true.
    @GetMapping("/park")
    public List<ParkingEvent> getParkingEvents() {
        return parkingEventRepo.findByisActive(true);
    }

    // Update parking event with carId to update endTime with 5 more minutes.
    @PatchMapping("/park/{carId}/add")
    public ParkingEvent updateParkingEvent(@PathVariable long carId) {
        Optional<Car> carOptional = carRepo.findById(carId);
        if (!carOptional.isPresent()) {
            throw new IllegalArgumentException("car not found..!");
        }
        Car car = carOptional.get();
        ParkingEvent parkingEvent = parkingEventRepo.findByCar(car);
        // Add 5 more minutes to endTime.
        parkingEvent.setEndTime(parkingEvent.getEndTime().plusSeconds(300));
        return parkingEventRepo.save(parkingEvent);
    }
}
