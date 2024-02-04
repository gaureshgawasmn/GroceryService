package com.techlab.grocery.application.service;

import com.techlab.grocery.application.entity.Grocery;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The GroceryService interface
 * <p>
 * This interface is used to perform CRUD operations on Grocery entity.
 */
public interface GroceryService {
    Grocery save(Grocery grocery);

    Grocery update(Grocery grocery);

    Grocery findById(int id);

    void deleteById(int id);

    List<Grocery> findAll();

    ResponseEntity<List<Grocery>> findAllGroceryEntity();
}
