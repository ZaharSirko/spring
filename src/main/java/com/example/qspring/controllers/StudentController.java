package com.example.qspring.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.qspring.model.Student;
import com.example.qspring.repo.StudentRepository;



@Controller
public class StudentController {

  @Autowired
  private StudentRepository studentRepository;

    @GetMapping("/student")
    public String studentMain(Model model){
      Iterable<Student> student = studentRepository.findAll();
      model.addAttribute("student", student);
      return "student";
    }
}
