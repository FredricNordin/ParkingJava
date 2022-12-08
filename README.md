# Parking Java assignment for school.
## Java 19, Spring Boot 3.0.0, MySQL,
 
### GET /api/person
  For a list of all persons.

### GET /api/person/ID
  To retrieve info about person with ID.

### GET /api/person/ID/car
  To retrieve all cars owned by person.

### POST /api/person
  {
  "name": "PersonName"
  }
  To add a person.

### POST /api/person/ID/car
  {
  "licencePlate": "ABC123"
  }
  To add a car to person with ID.

### GET /api/parkingspot
  Retrieve a list of all parkingspots.

### POST /api/parkingspot
  {
  "capacity": 20,
  "location": "Shopping mall 1"
  }
  To add a parkingspot at location with capacity.

### POST /api/park/personID/carId/parkingSpotID
  No JSON.
  all params are taken from URI to park a user with car at parkingspot.

### GET /api/park
  To retrieve a list of all active parkings.

### PATCH /api/park/carID/add
  No JSON.
  Will add 5 minutes to the carID endTime.
