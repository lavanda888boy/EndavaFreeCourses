package com.webapp.endavacourseproject.service;

import com.webapp.endavacourseproject.model.Mentor;
import com.webapp.endavacourseproject.model.dto.MentorDTO;
import com.webapp.endavacourseproject.model.dto.UserDTO;
import com.webapp.endavacourseproject.repository.MentorDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MentorService {

    private final MentorDAO mentorDAO;
    public void add(MentorDTO mentorDTO){
        Mentor mentor = new Mentor();

        mentor.setId(mentorDTO.getId());
        mentor.setFirstName(mentorDTO.getFirstName());
        mentor.setLastName(mentorDTO.getLastName());
        mentor.setEmail(mentorDTO.getEmail());
        mentor.setIndustries(mentorDTO.getIndustries());

        mentorDAO.save(mentor);
    }

    public List<UserDTO> getAll(Long limit){
        return null;
    }

    public void update(Long id, MentorDTO mentorDTO){

    }

    public void delete(Long id){
        mentorDAO.deleteById(id);
    }
}
