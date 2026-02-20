package com.suraj.SpringSecEg.controller;


import com.suraj.SpringSecEg.Model.Student;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1,"navin",60),
            new Student(2,"suraj",100),
            new Student(3,"chandan",70)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }


    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return(CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }
}
