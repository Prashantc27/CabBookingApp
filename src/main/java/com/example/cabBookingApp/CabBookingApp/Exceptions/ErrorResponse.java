package com.example.cabBookingApp.CabBookingApp.Exceptions;

import lombok.Data;

/**
 * This class represents an error response object used to communicate error details in the API response body.
 * It provides a simple structure to include an error message for the client.
 */
@Data
public class ErrorResponse {
	
	 private String message;
	 /**
     * Constructs a new ErrorResponse object with the specified error message.
     *
     * @param message The error message to be included in the response.
     */
	 public ErrorResponse(String message) {
	        this.message = message;
	 }
}
