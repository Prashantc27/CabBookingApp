package com.example.cabBookingApp.CabBookingApp.Dtos;

import lombok.Data;
/**
 * This class represents a data transfer object (DTO) used for capturing user details during user registration.
 * It serves as a way to decouple the request payload from the underlying user model.
 */
@Data
public class UserDetailRequest {
	   private String name;
	    private String gender;
	    private int age;
}
