package com.techlab.grocery.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * The AuthorizationService class
 * <p>
 * This class is used to retrieve the logged-in user id and other info.
 */
@Service
public class AuthorizationService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public String getLoggedInUserId() {
        // Retrieve the Authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Retrieve the username from the Authentication object
            username = authentication.getName();
            logger.info("Authenticated Username: " + username);
        } else {
            logger.error("User not authenticated");
        }
        return username;
    }
}
