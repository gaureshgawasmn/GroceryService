package com.techlab.grocery.application.dao;

import com.techlab.grocery.application.entity.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The GroceryRepository interface
 * <p>
 * This interface is used to perform CRUD operations on Grocery entity.
 */
@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Integer> {

}
