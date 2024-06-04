package com.example.cabBookingApp.CabBookingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cabBookingApp.CabBookingApp.Models.Driver;

/**
 * This interface extends JPA's JpaRepository and provides additional methods for accessing and manipulating Driver entities.
 * It allows for persistence operations and custom queries related to Driver data.
 */
public interface DriverRepository extends JpaRepository<Driver, Long> {
	
	/**
     * Finds all available drivers within a specified Manhattan distance from a given location.
     *
     * This method uses a custom JPQL query to filter drivers based on their availability and proximity.
     * The distance is calculated using the absolute difference between the driver's location and the provided coordinates (Manhattan distance).
     *
     * @param x The X-coordinate of the target location.
     * @param y The Y-coordinate of the target location.
     * @param distance The maximum allowed Manhattan distance (search radius).
     * @return A list of available Driver objects within the specified distance.
     */
	  @Query("SELECT d FROM Driver d WHERE d.available = true AND " +
	           "ABS(d.currentLocationX - :x) + ABS(d.currentLocationY - :y) <= :distance")
	    List<Driver> findAllByAvailableTrueAndWithinManhattanDistance(@Param("x") int x, @Param("y") int y, @Param("distance") int distance);
	    
	  /**
	     * Finds a driver by their name.
	     *
	     * This method leverages Spring Data's naming convention to automatically generate a JPQL query
	     * based on the method name (findByName).
	     *
	     * @param driverName The name of the driver to search for.
	     * @return A Driver object if found, null otherwise.
	     */
	  Driver findByName(String driverName);
	
	  /**
	     * Checks if a driver exists based on their vehicle details (assumed to be unique).
	     *
	     * This method leverages Spring Data's repository features to perform a quick existence check
	     * based on the unique `vehicleDetails` field.
	     *
	     * @param vehicleDetails The unique details of the driver's vehicle.
	     * @return True if a driver with the provided vehicle details exists, false otherwise.
	     */
	  boolean existsByVehicleDetails(String vehicleDetails);

	

	
}
