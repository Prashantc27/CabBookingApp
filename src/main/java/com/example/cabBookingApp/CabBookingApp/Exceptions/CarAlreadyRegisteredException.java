package com.example.cabBookingApp.CabBookingApp.Exceptions;

/**
 * This exception is thrown when a car with the provided details is already registered with another driver.
 * It extends {@link RuntimeException} to indicate an unexpected condition during driver registration.
 */
public class CarAlreadyRegisteredException extends RuntimeException {
	 /**
     * Constructs a new CarAlreadyRegisteredException with the specified message.
     *
     * @param message The message explaining why the car is already registered.
     */
    public CarAlreadyRegisteredException(String message) {
        super(message);
    }
}
