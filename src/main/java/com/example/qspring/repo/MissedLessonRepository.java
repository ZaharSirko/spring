package com.example.qspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.qspring.model.MissedLesson;

@Repository
public interface MissedLessonRepository extends JpaRepository<MissedLesson, Long>{
    
}
