package com.techlab.grocery.application.service.impl;

import com.techlab.grocery.application.config.AuthorizationService;
import com.techlab.grocery.application.dao.UserRepository;
import com.techlab.grocery.application.entity.DTO.UserDTO;
import com.techlab.grocery.application.entity.User;
import com.techlab.grocery.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The DefaultUserService class
 * <p>
 * This class implements UserService interface.
 */
@Service
public class DefaultUserService implements UserService {

    private UserRepository userRepository;

    private AuthorizationService authorizationService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserService(UserRepository userRepository, AuthorizationService authorizationService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorizationService = authorizationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isUserExist(String userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean updateUser(UserDTO user) {
        String userId = authorizationService.getLoggedInUserId();
        User userToUpdate = userRepository.findById(userId).orElse(null);
        if (userToUpdate == null) {
            return false;
        } else {
            if (user.getName() != null && !user.getName().isEmpty())
                userToUpdate.setName(user.getName());
            if (user.getMobileNumber() != null && !user.getMobileNumber().isEmpty())
                userToUpdate.setMobileNumber(user.getMobileNumber());
            if (user.getPassword() != null && !user.getPassword().isEmpty())
                userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(userToUpdate) != null;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setRole(user.getRole());
        }
        return user;
    }
}
