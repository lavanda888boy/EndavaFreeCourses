package com.webapp.endavacourseproject.service;

import com.webapp.endavacourseproject.model.User;
import com.webapp.endavacourseproject.model.dto.UserDTO;
import com.webapp.endavacourseproject.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    public void add(UserDTO userDTO){
        User user = new User();

        user.setId(userDTO.getId());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setActivityDomain(user.getActivityDomain());

        userDAO.save(user);
    }
}
