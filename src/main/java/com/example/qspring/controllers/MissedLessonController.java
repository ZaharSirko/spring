package com.example.qspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.qspring.model.MissedLesson;
import com.example.qspring.repo.MissedLessonRepository;

@Controller
public class MissedLessonController {

  @Autowired
  private MissedLessonRepository  missedLessonRepository;


    @GetMapping("/missed_lessons")
    public String groupMain(Model model){
      Iterable<MissedLesson> missedLesson = missedLessonRepository.findAll();
      model.addAttribute("missedLesson", missedLesson);
      return "missed_lessons";
    }
}
