package com.webapp.endavacourseproject;

import com.webapp.endavacourseproject.exceptionhandling.RestException;
import com.webapp.endavacourseproject.model.Mentor;
import com.webapp.endavacourseproject.model.dto.MentorDTO;
import com.webapp.endavacourseproject.repository.IndustryDAO;
import com.webapp.endavacourseproject.repository.MentorDAO;
import com.webapp.endavacourseproject.service.MentorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MentorServiceTest {

    @Mock
    private MentorDAO mentorDAO;

    @Mock
    private IndustryDAO industryDAO;

    @InjectMocks
    private MentorService mentorService;

    @Test
    public void getAllWithLimitTest() throws RestException {
        List<Mentor> list = new ArrayList<>();
        list.add(new Mentor(1L, "Seva", "Baj", "seva@mail.com", false));
        list.add(new Mentor(2L, "John", "Smith", "john@mail.com", false));

        when(mentorDAO.getAllMentors(2L)).thenReturn(list);

        List<MentorDTO> mentorList = mentorService.getAll(2L);

        assertEquals(2, mentorList.size());
        verify(mentorDAO, times(1)).getAllMentors(2L);
    }


    // TODO: in development
    /*
    @Test
    public void addTest() throws RestException {
        Mentor mentor = new Mentor(4L, "Jim", "Halpert", "jim@mail.com", false);
        MentorDTO mentorDTO = new MentorDTO(mentor);

        int size = mentorDAO.findAll().size();

        mentorService.add(mentorDTO);

        assertEquals(size + 1, mentorDAO.findAll().size());
        verify(mentorDAO, times(1)).save(mentor);
    }*/

    /*
    @Test
    public void deleteTest() throws RestException {
        Long id = 4L;
        int size = mentorDAO.findAll().size();
        mentorService.delete(id);

        assertEquals(size - 1, mentorDAO.findAll().size());
        verify(mentorDAO, times(1)).deleteById(id);
    }*/
}
