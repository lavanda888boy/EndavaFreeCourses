package com.webapp.endavacourseproject.service;

import com.webapp.endavacourseproject.exceptionhandling.RestException;
import com.webapp.endavacourseproject.model.Industry;
import com.webapp.endavacourseproject.model.Mentor;
import com.webapp.endavacourseproject.model.User;
import com.webapp.endavacourseproject.model.dto.UserDTO;
import com.webapp.endavacourseproject.repository.MentorDAO;
import com.webapp.endavacourseproject.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserDAO userDAO;

    private final MentorDAO mentorDAO;

    public void add(UserDTO userDTO) throws RestException{
        validateUser(userDTO);
        User user = new User(userDTO);

        try {
            assignMentor(user);
            userDAO.save(user);
            logger.info("A new user was added and a mentor was assigned to it", user);
        } catch (Exception e) {
            logger.error("User could not be added to the database!");
            throw new RestException("Database issue, cannot not add new user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<UserDTO> getAll(Long limit) throws RestException{
        if(limit != null && limit <= 0){
            logger.error("Wrong input data (limit less or equal than zero)!", limit);
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
            logger.info("Fetched the list of all users according to the limit", userDTOS.size());
            return userDTOS;
        } catch (Exception e) {
            logger.error("Users could not be fetched from the database!");
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
            logger.info("Successfully updated a user", updatedUser);
        } catch (Exception e) {
            logger.error("User could not be updated!");
            throw new RestException("Database issue, cannot update user", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(Long id) throws RestException{
        try {
            userDAO.deleteById(id);
            logger.info("User was deleted from the database", id);
        } catch (Exception e) {
            logger.error("User could not be deleted!");
            throw new RestException("Database issue, cannot delete user", HttpStatus.BAD_REQUEST);
        }
    }

    private void assignMentor(User user) throws RestException{
        List<Mentor> mentors = mentorDAO.getAllMentors();
        logger.info("List of mentors was extracted from the database", mentors.size());

        // TODO: rewrite the check whether the mentors' industries include user's activity domain
        if(user.getMentor() == null){
            for (Mentor mentor : mentors) {
                if(!mentor.isWorkingState()){
                    List<Industry> mentorIndustries = mentor.getIndustries();
                    for (Industry mind : mentorIndustries) {
                        if (mind.getIndustryName().compareTo(user.getActivityDomain()) == 0) {
                            user.setMentor(mentor);
                            break;
                        }
                    }
                }
                if(user.getMentor() != null){
                    break;
                }
            }
            if(user.getMentor() == null){
                logger.error("There were no mentors found for the current user");
                throw new RestException("There are no available mentors for such user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    private void validateUser(UserDTO userDTO) throws RestException{
        if(!validateName(userDTO.getFirstName())){
            logger.error("An invalid first name was introduced by the user: {}", userDTO.getFirstName());
            throw new RestException("Invalid first name", HttpStatus.BAD_REQUEST);
        } else if(!validateName(userDTO.getLastName())){
            logger.error("An invalid last name was introduced by the user", userDTO.getLastName());
            throw new RestException("Invalid last name", HttpStatus.BAD_REQUEST);
        } else if(!validateActivityDomain(userDTO.getActivityDomain())){
            logger.error("An invalid activity domain was introduced by the user", userDTO.getActivityDomain());
            throw new RestException("Invalid activity domain", HttpStatus.BAD_REQUEST);
        }
        logger.info("User was successfully validated");
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
            logger.info("User was found in the database", optionalUser.get());
            return;
        }
        logger.error("User was not found in the database!");
        throw new RestException("User not present", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
