package com.webapp.endavacourseproject.controller;

import com.webapp.endavacourseproject.model.dto.IndustryDTO;
import com.webapp.endavacourseproject.service.IndustryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/industries")
public class IndustryController {

    private final IndustryService industryService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNewIndustry(@RequestBody IndustryDTO industryDTO){
        try {
            industryService.add(industryDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllIndustries(@RequestBody(required = false) Long limit){
        try {
            return new ResponseEntity<>(industryService.getAll(limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<Object> updateIndustry(@PathVariable Long id, @RequestBody IndustryDTO industryDTO){
        try {
            industryService.update(id, industryDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteIndustry(@PathVariable Long id){
        try {
            industryService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
