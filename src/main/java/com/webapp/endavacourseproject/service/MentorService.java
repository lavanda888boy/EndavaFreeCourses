package com.webapp.endavacourseproject.service;

import com.webapp.endavacourseproject.exceptionhandling.RestException;
import com.webapp.endavacourseproject.model.Mentor;
import com.webapp.endavacourseproject.model.User;
import com.webapp.endavacourseproject.model.dto.MentorDTO;
import com.webapp.endavacourseproject.repository.MentorDAO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MentorService {

    private final MentorDAO mentorDAO;
    public void add(MentorDTO mentorDTO) throws RestException {
        Mentor mentor = new Mentor();

        try {
            mentorDAO.save(mentor);
        } catch (Exception e) {
            throw new RestException("Database issue, cannot add new mentor", HttpStatus.BAD_REQUEST);
        }
    }

    public List<MentorDTO> getAll(Long limit) throws RestException{
        try {
            List<Mentor> mentors;

            if (limit == null) {
                mentors = mentorDAO.getAllMentors();
            } else {
                mentors = mentorDAO.getAllMentors(limit);
            }

            List<MentorDTO> mentorDTOS = new ArrayList<>();

            for (Mentor mentor : mentors) {
                MentorDTO mdto = new MentorDTO(mentor);
                mentorDTOS.add(mdto);
            }
            return mentorDTOS;
        } catch (Exception e) {
            throw new RestException("Database issue, cannot get all mentors", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(Long id, MentorDTO mentorDTO) throws RestException{
        Optional<Mentor> optionalMentor = mentorDAO.findById(id);
        //userPresent(optionalUser);

        Mentor updatedMentor = optionalMentor.get();

        updatedMentor.setFirstName(mentorDTO.getFirstName());
        updatedMentor.setLastName(mentorDTO.getLastName());
        updatedMentor.setEmail(mentorDTO.getEmail());
        updatedMentor.setIndustries(mentorDTO.getIndustries());

        try {
            mentorDAO.save(updatedMentor);
        } catch (Exception e) {
            throw new RestException("Database issue, cannot update mentor", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(Long id) throws RestException{
        try {
            mentorDAO.deleteById(id);
        } catch (Exception e) {
            throw new RestException("Database issue, cannot delete mentor", HttpStatus.BAD_REQUEST);
        }
    }
}
