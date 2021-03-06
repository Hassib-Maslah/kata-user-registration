package com.kata.user.dao.repository;

import com.kata.user.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is User specific repository class
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
