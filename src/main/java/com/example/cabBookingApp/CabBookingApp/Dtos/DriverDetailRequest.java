package com.example.cabBookingApp.CabBookingApp.Dtos;

import lombok.Data;

/**
 * This class represents a data transfer object (DTO) used for capturing driver details during driver registration.
 * It serves as a way to decouple the request payload from the underlying driver model.
 */
@Data
public class DriverDetailRequest {
	
    private String name;
    private String gender;
    private int age;
    private String vehicleDetails;
    private int currentLocationX;
    private int currentLocationY;
    private boolean available;
}
