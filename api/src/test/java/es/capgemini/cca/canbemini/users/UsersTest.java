package es.capgemini.cca.canbemini.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsersTest {

    public static final String USER_EMAIL = "user@canbemeni.es";
    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersServiceImpl usersService;

    public static final Long EXISTS_USER_ID = 1L;
    private static final Long NOT_EXISTS_USER_ID = 2L;

    @Test
    public void findAllShouldReturnAllUsers() {

        List<Users> list = new ArrayList<>();
        list.add(mock(Users.class));

        when(usersRepository.findAll()).thenReturn(list);

        List<Users> users = usersService.findAll();

        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void saveNotExistsUsersIdShouldInsert() {

        UsersDto usersDto = new UsersDto();
        usersDto.setEmail(USER_EMAIL);

        ArgumentCaptor<Users> users = ArgumentCaptor.forClass(Users.class);

        usersService.saveUsers(null, usersDto);

        verify(usersRepository).save(users.capture());

        assertEquals(USER_EMAIL, users.getValue().getEmail());
    }

    @Test
    public void saveExistsUsersIdShouldUpdate() {

        UsersDto usersDto = new UsersDto();
        usersDto.setEmail(USER_EMAIL);

        Users users = mock(Users.class);

        when(usersRepository.findById(EXISTS_USER_ID)).thenReturn(Optional.of(users));

        usersService.saveUsers(EXISTS_USER_ID, usersDto);

        verify(usersRepository).save(users);
    }

    @Test
    public void deleteExistsUserIdShouldDelete() {

        usersService.deleteUsers(EXISTS_USER_ID);

        verify(usersRepository).deleteById(EXISTS_USER_ID);
    }

    @Test
    public void getExistsUsersIdShouldReturnUser() {

        Users users = mock(Users.class);
        when(users.getId()).thenReturn(EXISTS_USER_ID);
        when(usersRepository.findById(EXISTS_USER_ID)).thenReturn(Optional.of(users));

        Users usersResponse = usersService.findUsers(EXISTS_USER_ID);

        assertNotNull(usersResponse);
        assertEquals(EXISTS_USER_ID, users.getId());
    }

    @Test
    public void getNotExistsUsersIdShouldReturnNull() {

        when(usersRepository.findById(NOT_EXISTS_USER_ID)).thenReturn(Optional.empty());

        Users users = usersService.findUsers(NOT_EXISTS_USER_ID);

        assertNull(users);
    }

}
