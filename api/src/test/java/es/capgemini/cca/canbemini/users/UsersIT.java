package es.capgemini.cca.canbemini.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class UsersIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/api/user/";

    @InjectMocks
    UsersServiceImpl usersService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<UsersDto>> responseType = new ParameterizedTypeReference<List<UsersDto>>() {
    };

    public static final Long EXISTS_USERS_ID = 1L;
    public static final Long NOT_EXISTS_USERS_ID = 9L;
    public static final String NEW_USERS_EMAIL = "users@canbemini.es";
    public static final Long NEW_USERS_ID = 3L;
    private static final Long MODIFY_USERS_ID = 1L;
    private static final String MODIFY_USERS_EMAIL = "nuevouser@canbemini.es";
    private static final Long DELETE_USERS_ID = 1L;
    private static final Long EXIST_USERS_ID = 2L;

    @Test
    public void findAllShouldReturnAllUsers() {

        ResponseEntity<List<UsersDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_USERS_ID, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(5, response.getBody().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewUser() {
        UsersDto dto = new UsersDto();

        dto.setEmail(NEW_USERS_EMAIL);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<UsersDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXISTS_USERS_ID, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(6, response.getBody().size());

        UsersDto usersSearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_USERS_ID)).findFirst()
                .orElse(null);
        assertNotNull(usersSearch);
        assertEquals(NEW_USERS_EMAIL, usersSearch.getEmail());
    }

    @Test
    public void modifyWithExistIdShouldModifyUsers() {

        UsersDto dto = new UsersDto();
        dto.setEmail(MODIFY_USERS_EMAIL);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + MODIFY_USERS_ID, HttpMethod.PUT, new HttpEntity<>(dto),
                Void.class);

        ResponseEntity<List<UsersDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXISTS_USERS_ID, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(2, response.getBody().size());

        UsersDto usersSearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_USERS_ID))
                .findFirst().orElse(null);
        assertNotNull(usersSearch);
        assertEquals(MODIFY_USERS_EMAIL, usersSearch.getEmail());
    }

    @Test
    public void deleteWithExistsIdShouldDeleteUsers() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_USERS_ID, HttpMethod.DELETE, null, Void.class);

        ResponseEntity<List<UsersDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXISTS_USERS_ID, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(3, response.getBody().size());
    }
}
