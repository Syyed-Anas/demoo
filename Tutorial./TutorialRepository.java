package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Tutorial;

public interface TutorialRepository extends CrudRepository<Tutorial, Long>{
    
}
