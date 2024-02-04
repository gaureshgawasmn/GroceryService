package com.techlab.grocery.application.controller;

import com.techlab.grocery.application.entity.DTO.UserDTO;
import com.techlab.grocery.application.entity.User;
import com.techlab.grocery.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * The class UserController
 * <p>
 * Controller to handle all user related requests.
 */
@RestController
@RequestMapping("/v1")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Value("${spring.security.default.password}")
    private String defaultPassword;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(description = "Save user, Min Role: ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User saved successfully", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class), examples = {@ExampleObject(name = "Success", value = "User saved successfully")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/admin/users")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        if (userService.isUserExist(user.getUserId())) {
            logger.error("User already exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exist");
        }
        user.setPassword(passwordEncoder.encode(defaultPassword));
        User savedUser = userService.saveUser(user);
        if (savedUser != null) {
            logger.info("User saved successfully");
            return ResponseEntity.ok("User saved successfully");
        } else {
            logger.error("User not saved");
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Update user, Min Role: ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class), examples = {@ExampleObject(name = "Success", value = "User updated successfully")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/admin/users")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(defaultPassword));
        User updatedUser = userService.saveUser(user);
        if (updatedUser != null) {
            logger.info("Given User updated successfully");
            return ResponseEntity.ok("User updated successfully");
        } else {
            logger.error("User not updated");
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Delete user, Min Role: ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class), examples = {@ExampleObject(name = "Success", value = "User deleted successfully")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        logger.info("User deleted successfully");
        return ResponseEntity.ok("User deleted successfully");
    }

    @Operation(description = "Get user by userId, Min Role: ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class),
                            examples = {@ExampleObject(name = "example", value = "{\"userId\":\"UserId\",\"name\":\"User_Name\",\"mobileNumber\":\"940058896\",\"password\":\"7ddf32e17a6ac5ce04a8ecbf782ca509\",\"roles\":[\"ADMIN\",\"USER\"]}")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/admin/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            logger.info("User retrieved successfully");
            return ResponseEntity.ok(user);
        } else {
            logger.error("User not retrieved");
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Update self details, Min Role: USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details updated successfully", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class), examples = {@ExampleObject(name = "Success", value = "User details updated successfully")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/user/users")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO user) {
        boolean updatedUser = userService.updateUser(user);
        if (updatedUser) {
            logger.info("Given User updated successfully");
            return ResponseEntity.ok("User updated successfully");
        } else {
            logger.error("User not updated");
            return ResponseEntity.badRequest().build();
        }
    }
}
