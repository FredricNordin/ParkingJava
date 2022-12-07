# Parking Java assignment for school.
## Java 19, Spring Boot, MySQL,
 
### GET /person
  For a list of all persons.

### GET /person/ID
  To retrieve info about person with ID.

### GET /person/ID/cars
  To retrieve all cars owned by person.

### POST /person
  {
  "name": "PersonName"
  }
  To add a person.

### POST /person/ID/cars
  {
  "licencePlate": "ABC123"
  }
  To add a car to person with ID.

### GET /parkingspot
  Retrieve a list of all parkingspots.

### POST /parkingspot
  {
  "capacity": 20,
  "location": "Shopping mall 1"
  }
  To add a parkingspot at location with capacity.

### POST /park/personID/carId/parkingSpotID
  No JSON.
  all params are taken from URI to park a user with car at parkingspot.

### GET /park
  To retrieve a list of all active parkings.

### PATCH /park/1/add
  No JSON.
  Will add 5 minutes to the carID endTime.
