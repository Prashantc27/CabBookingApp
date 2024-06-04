package com.example.cabBookingApp.CabBookingApp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cabBookingApp.CabBookingApp.Models.User;

/**
 * This interface extends JPA's JpaRepository and provides additional methods for accessing and manipulating User entities.
 * It allows for persistence operations and custom queries related to User data.
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
     * Finds a user by their username.
     *
     * This method leverages Spring Data's naming convention to automatically generate a JPQL query
     * based on the method name (findByName).
     *
     * @param userName The username of the user to search for.
     * @return A User object if found, null otherwise.
     */
	User findByName(String userName);
}
