package com.suraj.SpringSecEg.Service;

import com.suraj.SpringSecEg.Model.Users;
import com.suraj.SpringSecEg.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    public Users register(Users user){

        user.setPassword(encoder.encode(user.getPassword()));


        return repo.save(user);
    }
}
