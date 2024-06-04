package com.example.cabBookingApp.CabBookingApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cabBookingApp.CabBookingApp.Dtos.UserDetailRequest;
import com.example.cabBookingApp.CabBookingApp.Exceptions.ErrorResponse;
import com.example.cabBookingApp.CabBookingApp.Exceptions.NoRideFoundException;
import com.example.cabBookingApp.CabBookingApp.Exceptions.RiderBookedAlreadyException;
import com.example.cabBookingApp.CabBookingApp.Models.Driver;
import com.example.cabBookingApp.CabBookingApp.Models.User;
import com.example.cabBookingApp.CabBookingApp.Services.CabBookingService;

/**
 * This class is a REST controller that handles user-related operations for a cab booking application.
 * It exposes endpoints for user registration, finding rides, and selecting the ride.
 */

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private CabBookingService cabBookingService;
    
    /**
     * Adds a new user to the system.
     * This method expects a {@link UserDetailRequest} object in the request body containing details
     * about the new user. The implementation details are delegated to the {@link CabBookingService}.
     */
    @PostMapping("/users")
    public User addUser(@RequestBody UserDetailRequest userDetailRequest) {
        return cabBookingService.addUser(userDetailRequest.getName(), userDetailRequest.getGender(), userDetailRequest.getAge());
    }
    
    /**
     * Finds available rides for a user based on their location and destination.
     * This method expects user credentials (username), source and destination coordinates as request parameters.
     * It delegates the logic of finding available rides to the {@link CabBookingService}.
     */
    @GetMapping("/find-ride")
    public ResponseEntity<?> findRide(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "sourceX") int sourceX, @RequestParam(value = "sourceY") int sourceY,
            @RequestParam(value = "destinationX") int destinationX, @RequestParam(value = "destinationY") int destinationY) {
        try {
            List<Driver> availableRides = cabBookingService.findRide(username, sourceX, sourceY, destinationX, destinationY);
            return ResponseEntity.ok().body(availableRides);
        } catch (NoRideFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    
    /**
     * Assigns a chosen driver to a user for a ride.
     * This method expects the username and driver name as request parameters. It delegates the logic of
     * assigning a driver to the {@link CabBookingService}.
     */
    @PostMapping("/choose-ride")
    public ResponseEntity<String> chooseRide(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "drivername") String driverName) {
        try {
            String message = cabBookingService.chooseRide(username, driverName);
            return ResponseEntity.ok().body(message);
        } catch (RiderBookedAlreadyException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());  // Return specific error message
        } catch (Exception ex) {  // Catch other unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
}
