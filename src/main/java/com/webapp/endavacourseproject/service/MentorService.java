package com.webapp.endavacourseproject.service;

import com.webapp.endavacourseproject.exceptionhandling.RestException;
import com.webapp.endavacourseproject.model.Mentor;
import com.webapp.endavacourseproject.model.dto.MentorDTO;
import com.webapp.endavacourseproject.repository.MentorDAO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<MentorDTO> getAll(Long limit){
        List<Mentor> mentors;

        if (limit == null) {
            mentors = mentorDAO.getAllMentors();
        } else {
            mentors = mentorDAO.getAllMentors(limit);
        }

        List<MentorDTO> mentorDTOS = new ArrayList<>();

        for (Mentor mentor : mentors) {
            MentorDTO mdto = new MentorDTO();

            mdto.setId(mentor.getId());
            mdto.setFirstName(mentor.getFirstName());
            mdto.setLastName(mentor.getLastName());
            mdto.setEmail(mentor.getEmail());
            mdto.setIndustries(mentor.getIndustries());

            mentorDTOS.add(mdto);
        }

        return mentorDTOS;
    }

    public void update(Long id, MentorDTO mentorDTO){

    }

    public void delete(Long id){
        mentorDAO.deleteById(id);
    }
}
