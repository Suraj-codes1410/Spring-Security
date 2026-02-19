package com.suraj.SpringSecEg.repo;

import com.suraj.SpringSecEg.Model.Student;
import com.suraj.SpringSecEg.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
