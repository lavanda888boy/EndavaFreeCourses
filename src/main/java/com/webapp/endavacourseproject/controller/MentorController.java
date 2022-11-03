package com.webapp.endavacourseproject.controller;

import com.webapp.endavacourseproject.model.dto.MentorDTO;
import com.webapp.endavacourseproject.service.MentorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/mentors")
public class MentorController {

    private final MentorService mentorService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNewMentor(@RequestBody MentorDTO mentorDTO){
        try {
            mentorService.add(mentorDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllMentors(@RequestBody(required = false) Long limit){
        try {
            return new ResponseEntity<>(mentorService.getAll(limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<Object> updateMentor(@PathVariable Long id, @RequestBody MentorDTO mentorDTO){
        try {
            mentorService.update(id, mentorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteMentor(@PathVariable Long id){
        try {
            mentorService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
