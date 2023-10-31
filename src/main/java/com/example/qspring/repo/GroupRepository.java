package com.example.qspring.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.qspring.model.Group;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT g FROM Group g WHERE g.group_name = :groupName")
    Optional<Group> findByGroupName(String groupName);

 
}
