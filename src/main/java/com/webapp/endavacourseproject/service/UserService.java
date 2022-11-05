package com.webapp.endavacourseproject.service;

import com.webapp.endavacourseproject.model.User;
import com.webapp.endavacourseproject.model.dto.UserDTO;
import com.webapp.endavacourseproject.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    public void add(UserDTO userDTO){
        User user = new User();

        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setActivityDomain(userDTO.getActivityDomain());

        userDAO.save(user);
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
}
