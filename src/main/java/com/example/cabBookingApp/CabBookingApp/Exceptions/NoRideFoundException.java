package com.example.cabBookingApp.CabBookingApp.Exceptions;

/**
 * This exception is thrown when no available rides are found near the user's location during a ride search.
 * It extends {@link RuntimeException} to indicate an unexpected condition during the search process.
 */
public class NoRideFoundException extends RuntimeException {
	
	/**
     * Constructs a new NoRideFoundException with the specified message.
     *
     * @param message The message explaining why no rides were found. (e.g., "No available drivers near your location")
     */
    public NoRideFoundException(String message) {
        super(message);
    }
}