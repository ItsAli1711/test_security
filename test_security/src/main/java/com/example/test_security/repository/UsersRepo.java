package com.example.test_security.repository;

import com.example.test_security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);
}
