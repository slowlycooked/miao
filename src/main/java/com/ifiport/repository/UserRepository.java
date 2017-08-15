package com.ifiport.repository;

/**
 * Created by Martin on 2017/08/14.
 */
import com.ifiport.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}

