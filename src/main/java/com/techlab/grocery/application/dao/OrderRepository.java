package com.techlab.grocery.application.dao;

import com.techlab.grocery.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The OrderRepository interface
 * <p>
 * This interface is used to perform CRUD operations on Order entity.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUserUserId(String userId);

}
