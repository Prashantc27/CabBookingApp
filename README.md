# CabBookingApp
Cab Booking Application
This application provides RESTful APIs for a cab booking system. It allows users to register, find available rides, and select a ride. It also enables drivers to register themselves in the system.

## Installation Clone this repository to your local machine.
bash Copy code git clone : 
git clone https://github.com/your-repository/cab-booking-app.git
cd cab-booking-app

## API Endpoints

### Add a Driver:
Endpoint: POST /drivers
#### Curl Request:
curl --location 'http://localhost:8080/drivers' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Driver1",
    "gender": "M",
    "age": 22,
    "vehicleDetails": "Swift, KA-01-12341",
    "currentLocationX": 10,
    "currentLocationY": 0
}'

### Add a User
Endpoint: POST /users
#### Curl Request:
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Abhishek",
    "gender": "M",
    "age": 23
}'

### Find a Ride
Endpoint: GET /find-ride

Request Parameters:
username (String): The username of the user.
sourceX (int): Source X coordinate.
sourceY (int): Source Y coordinate.
destinationX (int): Destination X coordinate.
destinationY (int): Destination Y coordinate.
#### Curl Request:
curl --location 'http://localhost:8080/find-ride?username=abhishek&sourceX=0&sourceY=0&destinationX=20&destinationY=1'

### Choose a Ride
Endpoint: POST /choose-ride
Request Parameters:
username (String): The username of the user.
drivername (String): The name of the driver.
#### Curl Request:
curl --location --request POST 'http://localhost:8080/choose-ride?username=Abhishek&drivername=Driver1'

