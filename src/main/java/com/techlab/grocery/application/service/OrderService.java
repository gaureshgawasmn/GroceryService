package com.techlab.grocery.application.service;

import com.techlab.grocery.application.entity.DTO.OrderDTO;
import com.techlab.grocery.application.entity.Order;

import java.util.List;

/**
 * The OrderService interface
 * <p>
 * This interface is used to perform CRUD operations on Order entity.
 */
public interface OrderService {
    OrderDTO save(OrderDTO order, String userId) throws Exception;

    Order findById(int id);

    void deleteById(int id);

    List<OrderDTO> findAllByUserId();

    boolean saveAll(List<OrderDTO> orders);
}
