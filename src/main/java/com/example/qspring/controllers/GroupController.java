package com.example.qspring.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.qspring.DatabaseLogger;
import com.example.qspring.model.Group;
import com.example.qspring.model.Student;
import com.example.qspring.repo.GroupRepository;
import com.example.qspring.repo.StudentRepository;


import jakarta.validation.Valid;


@Controller
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/groups")
    public String groupMain(Model model) {
        Iterable<Group> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @PostMapping("/groups")
    public String groupPostsAdd(@RequestParam String name){
        Group group = new Group(name.toUpperCase());
        groupRepository.save(group);
        DatabaseLogger.logChange("groups", name.toUpperCase());
        return "redirect:/groups";
    }

    @GetMapping("/groups/add")
    public String groupAdd(Model model) {
        return "groups-add";
    }

    @PostMapping("/groups/add")
    public String groupPostAdd(@Valid @ModelAttribute Group group, BindingResult bindingResult,@RequestParam String name) {
        group = new Group(name.toUpperCase());
        groupRepository.save(group);
        if (bindingResult.hasErrors()) {
            return "groups-add";
        }    
        return "redirect:/groups";
    }

    @GetMapping("/groups/{group_name}")
    public String groupDetails(@PathVariable(value = "group_name") String groupName, Model model) {
    Optional<Group> groupOptional = groupRepository.findByGroupName(groupName);
    if (groupOptional.isPresent()) {
        Group group = groupOptional.get();
        List<Student> students = studentRepository.findByGroup_Id(group.getGroup_id());
        model.addAttribute("student", students);
        return "groups-details";
    } else {
        return "group-not-found";
    }
}


@PostMapping("/groups/{group_name}/delete")
public String groupDelete(@RequestParam(value = "group_name") String groupName, Model model) {
    Optional<Group> groupOptional = groupRepository.findByGroupName(groupName);

    if (groupOptional.isPresent()) {
        Group group = groupOptional.get();
        groupRepository.delete(group);
        return "redirect:/groups";
    } else {
        return "group-not-found";
    }
}


}

