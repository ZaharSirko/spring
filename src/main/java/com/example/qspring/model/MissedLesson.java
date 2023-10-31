package com.example.qspring.model;

import java.time.LocalDate;

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
@Table(name = "missedlesson")
public class MissedLesson  {
    @Id
    @SequenceGenerator(
        name = "skip_sequence",
        sequenceName = "skip_sequence",
        allocationSize = 1
    )
    @GeneratedValue(    
        strategy = GenerationType.SEQUENCE,
        generator = "skip_sequence"
    )
    private Long skip_id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id") 
    private Student student;
    private Float skips_houre;
    private String skip_reason;
    @NotNull
    private LocalDate date;
    public MissedLesson(){};
    public MissedLesson(Long skip_id, Student student, Float skips_houre, String skip_reason, LocalDate date) {
        this.skip_id = skip_id;
        this.student = student;
        this.skips_houre = skips_houre;
        this.skip_reason = skip_reason;
        this.date = date;
    }
    public MissedLesson(Student student, Float skips_houre, String skip_reason, LocalDate date) {
        this.student = student;
        this.skips_houre = skips_houre;
        this.skip_reason = skip_reason;
        this.date = date;
    }
    public Float getSkips_houre() {
        return skips_houre;
    }
    public void setSkips_houre(Float skips_houre) {
        this.skips_houre = skips_houre;
    }
    public String getSkip_reason() {
        return skip_reason;
    }
    public void setSkip_reason(String skip_reason) {
        this.skip_reason = skip_reason;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Long getSkip_id() {
        return skip_id;
    }
    public void setSkip_id(Long skip_id) {
        this.skip_id = skip_id;
    }
}
