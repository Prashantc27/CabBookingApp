package com.example.cabBookingApp.CabBookingApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cabBookingApp.CabBookingApp.Dtos.DriverDetailRequest;
import com.example.cabBookingApp.CabBookingApp.Exceptions.CarAlreadyRegisteredException;
import com.example.cabBookingApp.CabBookingApp.Models.Driver;
import com.example.cabBookingApp.CabBookingApp.Services.CabBookingService;


/**
 * This class is a REST controller that handles driver-related operations for a cab booking application.
 * It exposes endpoints for adding new drivers.
 */

@RestController
public class DriverController {

    @Autowired
    private CabBookingService cabBookingService;
    
    /**
     * Adds a new driver to the system.
     * This method expects a {@link DriverDetailRequest} object in the request body containing details
     * about the new driver. If the vehicle details (provided in the request) are already registered with
     * another driver, a {@link CarAlreadyRegisteredException} is thrown.
     */
    @PostMapping("/drivers")
    public ResponseEntity<Object> addDriver(@RequestBody DriverDetailRequest driverDetailRequest) {
        try {
            Driver driver = cabBookingService.addDriver(driverDetailRequest.getName(), driverDetailRequest.getGender(),
                    driverDetailRequest.getAge(), driverDetailRequest.getVehicleDetails(),
                    driverDetailRequest.getCurrentLocationX(), driverDetailRequest.getCurrentLocationY());
            return ResponseEntity.ok().body(driver);
        } catch (CarAlreadyRegisteredException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
