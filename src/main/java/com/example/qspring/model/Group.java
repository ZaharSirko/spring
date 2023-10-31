package com.example.qspring.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @SequenceGenerator(
        name = "group_sequence",
        sequenceName = "group_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "group_sequence"
    )
    private Long group_id;
    @NotNull
    @Column(unique = true)
    private String group_name;
    public Group() {
    }
    public Group(Long group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }
    public Group(String group_name) {
        this.group_name = group_name;
    }
    public Group(Long group_id) {
        this.group_id = group_id;
    }
    public Long getGroup_id() {
        return group_id;
    }
    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }
    public String getGroup_name() {
        return group_name;
    }
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }


}
