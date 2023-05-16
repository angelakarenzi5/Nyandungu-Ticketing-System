package com.ticketing_system.repo;


import com.ticketing_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String email);
}
