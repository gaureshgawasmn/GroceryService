package com.techlab.grocery.application.service.impl;

import com.techlab.grocery.application.dao.GroceryRepository;
import com.techlab.grocery.application.entity.Grocery;
import com.techlab.grocery.application.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The DefaultGroceryService class
 * <p>
 * This class implements GroceryService interface.
 */
@Service
public class DefaultGroceryService implements GroceryService {

    private GroceryRepository repository;

    @Autowired
    public DefaultGroceryService(GroceryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Grocery save(Grocery grocery) {
        return repository.save(grocery);
    }

    @Override
    public Grocery update(Grocery grocery) {
        return repository.save(grocery);
    }

    @Override
    public Grocery findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Grocery> findAll() {
        return repository.findAll();
    }
}
