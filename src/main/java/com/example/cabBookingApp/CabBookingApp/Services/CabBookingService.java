package com.example.cabBookingApp.CabBookingApp.Services;

import java.util.List;

import com.example.cabBookingApp.CabBookingApp.Models.Driver;
import com.example.cabBookingApp.CabBookingApp.Models.User;

/**
 * This interface defines the services offered by the cab booking application.
 * It provides methods for user management, driver management, ride search, and ride assignment.
 */
public interface CabBookingService {
	
	/**
     * Adds a new user to the system.
     *
     * This method takes user details (name, gender, age) and delegates the logic of user creation
     * to the appropriate persistence layer (likely using UserRepository).
     *
     * @param name The user's name.
     * @param gender The user's gender.
     * @param age The user's age.
     * @return The newly created User object.
     */
	User addUser(String name, String gender, int age);
	
	/**
     * Adds a new driver to the system.
     *
     * This method takes driver details (name, gender, age, vehicle details, location) and delegates the logic of driver creation
     * to the appropriate persistence layer (likely using DriverRepository).
     *
     * @param name The driver's name.
     * @param gender The driver's gender.
     * @param age The driver's age.
     * @param vehicleDetails Details about the driver's vehicle.
     * @param currentLocationX The X-coordinate of the driver's current location.
     * @param currentLocationY The Y-coordinate of the driver's current location.
     * @return The newly created Driver object.
     */
	Driver addDriver(String name, String gender, int age, String vehicleDetails, int currentLocationX, int currentLocationY);
	
	 /**
     * Finds available rides for a user based on their location and destination.
     *
     * This method takes user credentials (username), source and destination coordinates as arguments.
     * It likely delegates the logic of finding available rides to the DriverRepository
     * (considering available drivers within a certain distance) and returns a list of matching drivers.
     *
     * @param username The username of the user requesting a ride.
     * @param sourceX The X-coordinate of the user's source location.
     * @param sourceY The Y-coordinate of the user's source location.
     * @param destinationX The X-coordinate of the user's destination.
     * @param destinationY The Y-coordinate of the user's destination.
     * @return A list of available Driver objects or throws an exception if no rides are found.
     * @throws Exception - Potential exceptions related to ride search logic (e.g., NoRideFoundException)
     */
	List<Driver> findRide(String username, int sourceX, int sourceY, int destinationX, int destinationY);
	
	 /**
     * Assigns a chosen driver to a user for a ride.
     *
     * This method takes the username and driver name as arguments. It likely delegates the logic of
     * assigning a driver to the persistence layer and handles potential conflicts (e.g., driver already booked).
     *
     * @param username The username of the user requesting a ride.
     * @param driverName The name of the driver chosen by the user.
     * @return A message indicating successful ride assignment or throws an exception in case of errors.
     * @throws Exception - Potential exceptions related to ride assignment (e.g., RiderBookedAlreadyException)
     */
	String chooseRide(String username, String driverName);
}
