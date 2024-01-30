package com.techlab.grocery.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * The User entity
 * <p>
 * This class is used to create user entity.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_details")
public class User {
    @Id
    private String userId;
    private String name;
    private String mobileNumber;
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> orderList = new ArrayList<>();
    @Transient
    private List<Role> roles = new ArrayList<>();
    @JsonIgnore
    private String role = "";

    public User setRole(String role) {
        this.role = role;
        this.roles = new ArrayList<>();
        if (role != null && !role.isEmpty()) {
            for (String r : role.split(",")) {
                this.roles.add(Role.valueOf(r));
            }
        }
        return this;
    }

    public User setRoles(List<Role> roles) {
        this.roles = roles;
        roles.stream().forEach(role -> {
            this.role += role.toString() + ",";
        });
        return this;
    }

}
