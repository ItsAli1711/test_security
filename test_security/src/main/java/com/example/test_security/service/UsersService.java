package com.example.test_security.service;

import com.example.test_security.model.Users;
import com.example.test_security.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepo repo;
    public void save(Users user) {
        repo.save(user);
    }
}
