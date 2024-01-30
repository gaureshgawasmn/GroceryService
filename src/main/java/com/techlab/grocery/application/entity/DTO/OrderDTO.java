package com.techlab.grocery.application.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The OrderDTO class
 * <p>
 * This class is used to create order DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private int groceryId;
    private int quantity;
    private double totalPrice;
}
