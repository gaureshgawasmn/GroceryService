package com.techlab.grocery.application.service;

import com.techlab.grocery.application.entity.DTO.UserDTO;
import com.techlab.grocery.application.entity.User;

/**
 * The UserService interface
 * <p>
 * This interface is used to perform CRUD operations on User entity.
 */
public interface UserService {
    boolean isUserExist(String userId);

    User saveUser(User user);

    boolean updateUser(UserDTO user);

    void deleteUser(String userId);

    User getUser(String userId);
}
