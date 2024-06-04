package com.example.cabBookingApp.CabBookingApp.Models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a driver in the cab booking application.
 * It holds information about the driver's details and availability.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String gender;
	private int age;
	
	@Column(unique = true)  // Adding unique constraint on vehicleDetails
	private String vehicleDetails;
	private int currentLocationX;
	private int currentLocationY;
	private boolean available;
	
	public Driver(String name, String gender, int age, String vehicleDetails, int currentLocationX, int currentLocationY, boolean available) {
	    this.name = name;
	    this.gender = gender;
	    this.age = age;
	    this.vehicleDetails = vehicleDetails;
	    this.currentLocationX = currentLocationX;
	    this.currentLocationY = currentLocationY;
	    this.available = available;
	}
	
}
