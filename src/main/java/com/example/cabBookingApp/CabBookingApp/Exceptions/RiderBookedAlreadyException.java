package com.example.cabBookingApp.CabBookingApp.Exceptions;

/**
 * This exception is thrown when a user tries to choose a driver who is already booked by another user.
 * It extends {@link RuntimeException} to indicate an unexpected condition during ride assignment.
 */
public class RiderBookedAlreadyException extends RuntimeException {
	
	/**
     * Constructs a new RiderBookedAlreadyException with the specified message.
     *
     * @param message The message explaining why the chosen driver is already booked. (e.g., "Driver is already booked by another user")
     */
    public RiderBookedAlreadyException(String message) {
        super(message);
    }
}