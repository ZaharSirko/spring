package com.example.qspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    private Long student_id;
    @NotNull
    private String std_name;
    @NotNull
    private String surename;
    @NotNull
    private String address;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "group_id") 
    private Group group;

    public Student() {
    }
    public Student(Group group, Long student_id, String std_name, String surename,
            String address) {
        this.group = group;
        this.student_id = student_id;
        this.std_name = std_name;
        this.surename = surename;
        this.address = address;
    }
    public Student(Group group, String std_name, String surename, String address) {
        this.group = group;
        this.std_name = std_name;
        this.surename = surename;
        this.address = address;
    }
    public Student(Long student_id) {
        this.student_id = student_id;
    }
    public Long getStudent_id() {
        return student_id;
    }
    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }
    public String getStd_name() {
        return std_name;
    }
    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }
    public String getSurename() {
        return surename;
    }
    public void setSurename(String surename) {
        this.surename = surename;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
}
