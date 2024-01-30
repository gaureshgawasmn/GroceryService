package com.techlab.grocery.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Grocery entity
 * <p>
 * This class is used to create grocery entity.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grocery {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double price;
    private int quantity;
}
