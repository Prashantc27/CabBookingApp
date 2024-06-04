package com.example.cabBookingApp.CabBookingApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cabBookingApp.CabBookingApp.Exceptions.CarAlreadyRegisteredException;
import com.example.cabBookingApp.CabBookingApp.Exceptions.NoRideFoundException;
import com.example.cabBookingApp.CabBookingApp.Exceptions.RiderBookedAlreadyException;
import com.example.cabBookingApp.CabBookingApp.Models.Driver;
import com.example.cabBookingApp.CabBookingApp.Models.User;
import com.example.cabBookingApp.CabBookingApp.Repository.DriverRepository;
import com.example.cabBookingApp.CabBookingApp.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * **Cab Booking Service Implementation**
This class implements the `CabBookingService` interface and provides concrete implementations for the functionalities related to user and driver management, ride search, and ride assignment in the cab booking application.
*/
@RequiredArgsConstructor
@Service
public class CabBookingServiceImpl implements CabBookingService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;
    
    /**
     * Adds a new user to the system.
     *
     * This method creates a new `User` object with the provided details (name, gender, age)
     * and persists it to the database using the `userRepository.save(user)`.
     */
    public User addUser(String name, String gender, int age) {
        User user = new User();
        user.setName(name);
        user.setGender(gender);
        user.setAge(age);
        return userRepository.save(user);
    }
    
    /**
     * Adds a new driver to the system.
     *
     * This method performs the following steps:
     *
     * 1. Checks if a driver with the provided `vehicleDetails` already exists using `driverRepository.existsByVehicleDetails(vehicleDetails)`.
     * 2. Throws a `CarAlreadyRegisteredException` if a duplicate vehicle is found.
     * 3. Creates a new `Driver` object with the provided details and sets `available` to `true` (assuming driver is available by default).
     * 4. Saves the driver to the database using `driverRepository.save(driver)`.
     * 5. Returns the newly created `Driver` object.
     */
    public Driver addDriver(String name, String gender, int age, String vehicleDetails, int currentLocationX, int currentLocationY) throws CarAlreadyRegisteredException {
        if (driverRepository.existsByVehicleDetails(vehicleDetails)) {
            throw new CarAlreadyRegisteredException("Vehicle with details '" + vehicleDetails + "' is already registered.");
        }

        Driver driver = new Driver(name, gender, age, vehicleDetails, currentLocationX, currentLocationY, true);
        return driverRepository.save(driver);
    }
        
    /**
     * Finds available rides for a user based on their location and destination.
     *
     * This method performs the following steps:
     *
     * 1. Finds available drivers within a Manhattan distance of 5 units from the source location using `driverRepository.findAllByAvailableTrueAndWithinManhattanDistance(sourceX, sourceY, 5)`.
     * 2. Throws a `NoRideFoundException` if no available drivers are found.
     * 3. Returns a list of available `Driver` objects.
     */
    public List<Driver> findRide(String username, int sourceX, int sourceY, int destinationX, int destinationY) throws NoRideFoundException {
        List<Driver> availableDrivers = driverRepository.findAllByAvailableTrueAndWithinManhattanDistance(sourceX, sourceY, 5);
        if (availableDrivers.isEmpty()) {
            throw new NoRideFoundException("No ride found");
        }
        return availableDrivers;
    }
    
    /**
     * Assigns a chosen driver to a user for a ride.
     *
     * This method performs the following steps:
     *
     * 1. Attempts to find a driver by name using `driverRepository.findByName(driverName)`.
     * 2. Returns "Driver not found" if the driver is not found.
     * 3. Checks if the found driver is available using `driver.isAvailable()`.
     * 4. Throws a `RiderBookedAlreadyException` if the driver is already booked.
     * 5. Sets the driver's `available` flag to `false` to indicate a booking.
     * 6. Saves the updated driver information using `driverRepository.save(driver)`.
     * 7. Returns "Driver Booked Successfully!!" on successful booking.
     */
    public String chooseRide(String username, String driverName) throws RiderBookedAlreadyException {
        Driver driver = driverRepository.findByName(driverName);
        if (driver == null) {
            return "Driver not found"; 
        } else if (!driver.isAvailable()) {
            throw new RiderBookedAlreadyException("Driver is already booked!");  
        } else {
            driver.setAvailable(false);
            driverRepository.save(driver);
            return "Driver Booked Successfully!!";
        }
    }
    
}
