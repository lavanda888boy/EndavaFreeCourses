package com.webapp.endavacourseproject.controller;

import com.webapp.endavacourseproject.model.dto.UserDTO;
import com.webapp.endavacourseproject.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @SecurityRequirement(name = "averageSpringFan")
    @SecurityRequirement(name = "averageSpringEnjoyer")
    @PostMapping("/add")
    public ResponseEntity<Object> addNewUser(@RequestBody UserDTO userDTO){
        try {
            userService.add(userDTO);
            return new ResponseEntity<>("A new user was created, a mentor was assigned to it", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @SecurityRequirement(name = "averageSpringFan")
    @SecurityRequirement(name = "averageSpringEnjoyer")
    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers(@RequestParam(required = false) Long limit){
        try {
            return new ResponseEntity<>(userService.getAll(limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @SecurityRequirement(name = "averageSpringEnjoyer")
    @PatchMapping("update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        try {
            userService.update(id, userDTO);
            return new ResponseEntity<>("A user was updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @SecurityRequirement(name = "averageSpringEnjoyer")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        try {
            userService.delete(id);
            return new ResponseEntity<>("A user was deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
