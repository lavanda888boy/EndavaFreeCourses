package com.webapp.endavacourseproject.service;

import com.webapp.endavacourseproject.exceptionhandling.RestException;
import com.webapp.endavacourseproject.model.Industry;
import com.webapp.endavacourseproject.model.Mentor;
import com.webapp.endavacourseproject.model.dto.MentorDTO;
import com.webapp.endavacourseproject.repository.IndustryDAO;
import com.webapp.endavacourseproject.repository.MentorDAO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class MentorService {

    private final Logger logger = LoggerFactory.getLogger(MentorService.class);

    private final MentorDAO mentorDAO;

    private final IndustryDAO industryDAO;

    public void add(MentorDTO mentorDTO) throws RestException {
        validateMentor(mentorDTO);
        Mentor mentor = new Mentor(mentorDTO);

        try {
            assignIndustries(mentor);
            mentorDAO.save(mentor);
            logger.info("A new mentor was added and industries were assigned to it: {}", mentor);
        } catch (Exception e) {
            logger.error("Mentor could not be added to the database!");
            throw new RestException("Database issue, cannot add new mentor", HttpStatus.BAD_REQUEST);
        }
    }

    public List<MentorDTO> getAll(Long limit) throws RestException {
        if (limit != null && limit <= 0) {
            logger.error("Wrong input data (limit less or equal than zero)!, limit = {}", limit);
            throw new RestException("Unacceptable limit (negative or zero)", HttpStatus.BAD_REQUEST);
        }

        try {
            List<Mentor> mentors;

            if (limit == null) {
                mentors = mentorDAO.findAll();
            } else {
                mentors = mentorDAO.getAllMentors(limit);
            }

            List<MentorDTO> mentorDTOS = new ArrayList<>();

            for (Mentor mentor : mentors) {
                MentorDTO mdto = new MentorDTO(mentor);
                mentorDTOS.add(mdto);
            }
            logger.info("Fetched the list of all mentors according to the limit, size = {}", mentorDTOS.size());
            return mentorDTOS;
        } catch (Exception e) {
            logger.error("Mentors could not be fetched from the database!");
            throw new RestException("Database issue, cannot get all mentors", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(Long id, MentorDTO mentorDTO) throws RestException {
        Optional<Mentor> optionalMentor = mentorDAO.findById(id);
        mentorPresent(optionalMentor);
        validateMentor(mentorDTO);

        Mentor updatedMentor = optionalMentor.get();

        updatedMentor.setFirstName(mentorDTO.getFirstName());
        updatedMentor.setLastName(mentorDTO.getLastName());
        updatedMentor.setEmail(mentorDTO.getEmail());
        updatedMentor.setWorkingState(mentorDTO.isWorkingState());

        try {
            mentorDAO.save(updatedMentor);
            logger.info("Successfully updated a mentor: {}", updatedMentor);
        } catch (Exception e) {
            logger.error("Mentor could not be updated!");
            throw new RestException("Database issue, cannot update mentor", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(Long id) throws RestException {
        try {
            mentorDAO.deleteById(id);
            logger.info("Mentor was deleted from the database, id = {}", id);
        } catch (Exception e) {
            logger.error("Mentor could not be deleted!");
            throw new RestException("Database issue, cannot delete mentor", HttpStatus.BAD_REQUEST);
        }
    }

    private void assignIndustries(Mentor mentor) throws RestException {
        List<Industry> industries = industryDAO.findAll();
        logger.info("List of industries was extracted from the database, size = {}", industries.size());

        if (industries == null) {
            logger.error("The industries table is empty!");
            throw new RestException("No industries were extracted from the database", HttpStatus.BAD_REQUEST);
        }

        List<Industry> suitableIndustries = new LinkedList<>();

        Random r = new Random();
        int index;
        for (int i = 0; i < 3; i++) {
            index = r.nextInt(industries.size());
            Industry ind = industries.get(index);

            if (suitableIndustries.size() == 0) {
                suitableIndustries.add(ind);
            } else {
                if (!suitableIndustries.contains(ind)) {
                    suitableIndustries.add(ind);
                }
            }
        }
        mentor.setIndustries(suitableIndustries);
        logger.info("Industries were assigned to the mentor");
    }

    // TODO: make more informative validation
    private void validateMentor(MentorDTO mentorDTO) throws RestException {
        if (!validateName(mentorDTO.getFirstName())) {
            logger.error("An invalid first name was introduced for the mentor (should be 1-10 characters long)", mentorDTO.getFirstName());
            throw new RestException("Invalid first name", HttpStatus.BAD_REQUEST);
        } else if (!validateName(mentorDTO.getLastName())) {
            logger.error("An invalid last name was introduced for the mentor (should be 1-10 characters long)", mentorDTO.getLastName());
            throw new RestException("Invalid last name", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validateName(String name) {
        return !name.isEmpty() && !name.contains(" ") && name.length() >= 1 && name.length() <= 10;
    }

    private void mentorPresent(Optional<Mentor> optionalMentor) throws RestException {
        if (optionalMentor.isPresent()) {
            logger.info("Mentor was found in the database: {}", optionalMentor.get());
            return;
        }
        logger.error("Mentor was not found in the database!");
        throw new RestException("User not present", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
