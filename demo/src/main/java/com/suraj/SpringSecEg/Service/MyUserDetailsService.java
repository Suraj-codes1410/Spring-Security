package com.suraj.SpringSecEg.Service;

import com.suraj.SpringSecEg.Model.Users;
import com.suraj.SpringSecEg.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tools.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findByUsername(username);

        if(user==null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }
        return new ;
    }
}
