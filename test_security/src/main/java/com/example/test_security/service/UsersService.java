package com.example.test_security.service;

import com.example.test_security.model.Users;
import com.example.test_security.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepo repo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);           //create a encoder type object


    public void register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));      //encode the password given by user
        repo.save(user);                                           // store the encoded password
    }
}
