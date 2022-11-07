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

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    public void add(UserDTO userDTO) throws RestException{
        User user = new User(userDTO);

        try {
            userDAO.save(user);
        } catch (Exception e) {
            throw new RestException("Database issue, could not add new user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<UserDTO> getAll(Long limit) throws RestException{
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

    }

    public void delete(Long id) throws RestException{
        try {
            userDAO.deleteById(id);
        } catch (Exception e) {
            throw new RestException("Database issue, cannot delete user", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validateName(String name) throws RestException {
        if(!name.isEmpty() && !name.contains(" ") && name.length() >= 1 && name.length() <= 10){
            return true;
        }
        return false;
    }
}
