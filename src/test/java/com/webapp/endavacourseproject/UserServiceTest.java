package com.webapp.endavacourseproject;

import com.webapp.endavacourseproject.exceptionhandling.RestException;
import com.webapp.endavacourseproject.model.User;
import com.webapp.endavacourseproject.model.dto.UserDTO;
import com.webapp.endavacourseproject.repository.UserDAO;
import com.webapp.endavacourseproject.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Test
    public void getAllWithLimitTest() throws RestException {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "John", "Burns", "john@gmail.com", "sport"));

        when(userDAO.getAllUsers(1L)).thenReturn(list);

        List<UserDTO> userList = userService.getAll(1L);

        assertEquals(1, userList.size());
        verify(userDAO, times(1)).getAllUsers(1L);
    }


    // TODO: in development
    /*
    @Test
    public void addTest() throws RestException {
        User user = new User(3L, "John", "Winehouse", "john@gmail.com", "business");
        UserDTO userDTO = new UserDTO(user);

        int size = userDAO.findAll().size();

        userService.add(userDTO);

        assertEquals(size + 1, userDAO.findAll().size());
        verify(userDAO, times(1)).save(user);
    }*/

    /*
    @Test
    public void deleteTest() throws RestException {
        Long id = 3L;
        int size = userDAO.findAll().size();
        userService.delete(id);

        assertEquals(size - 1, userDAO.findAll().size());
        verify(userDAO, times(1)).deleteById(id);
    }*/
}
