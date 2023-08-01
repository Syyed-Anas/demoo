package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;

@RestController
@RequestMapping("/api")
public class TutorialController{

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/show_all")

    public List<Tutorial> getAllTutorials(){
        return (List<Tutorial>) tutorialRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
        Tutorial _tutorial= tutorialRepository
        .save(new Tutorial(tutorial.getFirstname(),tutorial.getLastname(),tutorial.getPassword()));

        return new ResponseEntity<Tutorial>(_tutorial, HttpStatus.CREATED);
    }
    //return new ResponseEntity<> (_tutorial.HttpStatus.CREATED)

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllTutorial(){
        tutorialRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/insert/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") Long id,@RequestBody Tutorial tutorial){
        Optional<Tutorial> __tutorial = tutorialRepository.findById(id);
        if(__tutorial.isPresent()){
            Tutorial _tutorial = __tutorial.get();
            _tutorial.setFirstname(tutorial.getFirstname());
            _tutorial.setLastname(tutorial.getLastname());
            _tutorial.setPassword(tutorial.getPassword());

            return new ResponseEntity<> (tutorialRepository.save(_tutorial),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

}
