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
        validateName(userDTO.getFirstName(), userDTO.getLastName());
        User user = new User(userDTO);

        try {
            userDAO.save(user);
        } catch (Exception e) {
            throw new RestException("Database issue, could not add new user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<UserDTO> getAll(Long limit){
        List<User> users;

        if (limit == null) {
            users = userDAO.getAllUsers();
        } else {
            users = userDAO.getAllUsers(limit);
        }
        
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            UserDTO udto = new UserDTO();

            udto.setId(user.getId());
            udto.setFirstName(user.getFirstName());
            udto.setLastName(user.getLastName());
            udto.setEmail(user.getEmail());
            udto.setActivityDomain(user.getActivityDomain());

            userDTOS.add(udto);
        }

        return userDTOS;
    }

    public void update(Long id, UserDTO userDTO){

    }

    public void delete(Long id){
        userDAO.deleteById(id);
    }

    private void validateName(String firstName, String lastName) throws RestException {
        if(firstName.length() <= 10  &&  lastName.length() <= 10){
            return;
        }
        throw new RestException("Too long first name or last name", HttpStatus.BAD_REQUEST);
    }
}
