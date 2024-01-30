package com.techlab.grocery.application.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The UserDTO class
 * <p>
 * This class is used to create user DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String mobileNumber;
    private String password;
}
