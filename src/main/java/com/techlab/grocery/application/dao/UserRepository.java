package com.techlab.grocery.application.dao;

import com.techlab.grocery.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The UserRepository interface
 * <p>
 * This interface is used to perform CRUD operations on User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
