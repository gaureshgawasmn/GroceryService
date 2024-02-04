package com.techlab.grocery.application.service.impl;

import com.techlab.grocery.application.dao.GroceryRepository;
import com.techlab.grocery.application.entity.Grocery;
import com.techlab.grocery.application.service.GroceryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * The DefaultGroceryService class
 * <p>
 * This class implements GroceryService interface.
 */
@Service
public class DefaultGroceryService implements GroceryService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

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

    @Override
    public ResponseEntity<List<Grocery>> findAllGroceryEntity() {
        List<Grocery> groceries = findAll();
        if (groceries != null) {
            logger.info("Groceries found successfully");
            return ResponseEntity.ok(groceries);
        } else {
            logger.error("Groceries not found");
            throw HttpServerErrorException.create(HttpStatus.NOT_FOUND, "Groceries not found", null, null, null);
        }
    }
}
