package com.webapp.endavacourseproject.service;

import com.webapp.endavacourseproject.exceptionhandling.RestException;
import com.webapp.endavacourseproject.model.User;
import com.webapp.endavacourseproject.model.dto.UserDTO;
import com.webapp.endavacourseproject.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    public void add(UserDTO userDTO) throws RestException{
        validateUser(userDTO);
        User user = new User(userDTO);

        try {
            userDAO.save(user);
        } catch (Exception e) {
            throw new RestException("Database issue, cannot not add new user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<UserDTO> getAll(Long limit) throws RestException{
        if(limit <= 0){
            throw new RestException("Unacceptable limit (negative or zero)", HttpStatus.BAD_REQUEST);
        }

        try {
            List<User> users;

            if (limit == null) {
                users = userDAO.getAllUsers();
            } else {
                users = userDAO.getAllUsers(limit);
            }

            List<UserDTO> userDTOS = new ArrayList<>();

            for (User user : users) {
                UserDTO udto = new UserDTO(user);
                userDTOS.add(udto);
            }
            return userDTOS;
        } catch (Exception e) {
            throw new RestException("Database issue, cannot get all users", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(Long id, UserDTO userDTO) throws RestException{
        Optional<User> optionalUser = userDAO.findById(id);
        userPresent(optionalUser);
        validateUser(userDTO);

        User updatedUser = optionalUser.get();

        updatedUser.setFirstName(userDTO.getFirstName());
        updatedUser.setLastName(userDTO.getLastName());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setActivityDomain(userDTO.getActivityDomain());

        try {
            userDAO.save(updatedUser);
        } catch (Exception e) {
            throw new RestException("Database issue, cannot update user", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(Long id) throws RestException{
        try {
            userDAO.deleteById(id);
        } catch (Exception e) {
            throw new RestException("Database issue, cannot delete user", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUser(UserDTO userDTO) throws RestException{
        if(!validateName(userDTO.getFirstName())){
            throw new RestException("Invalid first name", HttpStatus.BAD_REQUEST);
        } else if(!validateName(userDTO.getLastName())){
            throw new RestException("Invalid last name", HttpStatus.BAD_REQUEST);
        } else if(!validateActivityDomain(userDTO.getActivityDomain())){
            throw new RestException("Invalid activity domain", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validateName(String name){
        if(!name.isEmpty() && !name.contains(" ") && name.length() >= 1 && name.length() <= 10){
            return true;
        }
        return false;
    }

    private boolean validateActivityDomain(String domain){
        if(!domain.isEmpty() && domain.length() >= 2 && domain.length() <= 10){
            return true;
        }
        return false;
    }

    private void userPresent(Optional<User> optionalUser) throws RestException{
        if(optionalUser.isPresent()){
            return;
        }
        throw new RestException("User not present", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
