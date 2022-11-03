package com.webapp.endavacourseproject.service;

import com.webapp.endavacourseproject.model.Industry;
import com.webapp.endavacourseproject.model.dto.IndustryDTO;
import com.webapp.endavacourseproject.model.dto.UserDTO;
import com.webapp.endavacourseproject.repository.IndustryDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IndustryService {

    private final IndustryDAO industryDAO;
    public void add(IndustryDTO industryDTO){
        Industry industry = new Industry();

        industry.setId(industryDTO.getId());
        industry.setIndustryName(industryDTO.getIndustryName());
    }

    public List<UserDTO> getAll(Long limit){
        return null;
    }

    public void update(Long id, IndustryDTO industryDTO){

    }

    public void delete(Long id){
        industryDAO.deleteById(id);
    }
}

