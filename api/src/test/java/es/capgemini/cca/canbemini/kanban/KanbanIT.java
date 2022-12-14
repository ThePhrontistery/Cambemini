package es.capgemini.cca.canbemini.kanban;

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

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionServiceImpl;
import es.capgemini.cca.canbemini.users.UsersServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class KanbanIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/api/kanban/";
    public static final String SERVICE_PATH_SAVE = "/api/kanban/save/";

    @InjectMocks
    KanbanServiceImpl kanbanService;

    @InjectMocks
    UsersServiceImpl userService;

    @InjectMocks
    UserKanbanPermissionServiceImpl ukpService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<KanbanDto>> responseType = new ParameterizedTypeReference<List<KanbanDto>>() {
    };

    public static final Long EXISTS_KANBAN_ID = 1L;
    public static final Long NOT_EXISTS_KANBAN_ID = 9L;
    public static final String NEW_KANBAN_TITLE = "KANBAN1";
    public static final Long NEW_KANBAN_ID = 3L;
    private static final Long DELETE_KANBAN_ID = 1L;
    private static final Long MODIFY_KANBAN_ID = 2L;
    private static final String MODIFY_KANBAN_TITLE = "A second Kanban EDIT";
    private static final Long EXIST_USER_ID = 2L;
    public static final String NEW_KANBAN_DESCRIPTION = "New Kanban Description";

    @Test
    public void findAllShouldReturnAllUserKanbans() {

        ResponseEntity<List<KanbanDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_USER_ID, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewKanban() {
        KanbanDto kanbanDto = new KanbanDto();
        kanbanDto.setTitle(NEW_KANBAN_TITLE);
        kanbanDto.setDescription(NEW_KANBAN_DESCRIPTION);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH_SAVE + EXIST_USER_ID, HttpMethod.PUT,
                new HttpEntity<>(kanbanDto), Void.class);

        // restTemplate.exchange(LOCALHOST + port + SERVICE_PATH_UKP, HttpMethod.PUT,
        // new HttpEntity<>(kanbanDto),Void.class);
        // ukpService.saveUkp(null, EXIST_USER_ID, EXISTS_KANBAN_ID, 1L);

        ResponseEntity<List<KanbanDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_USER_ID, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());

        KanbanDto kanbanSearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_KANBAN_ID))
                .findFirst().orElse(null);
        assertNotNull(kanbanSearch);
        assertEquals(NEW_KANBAN_TITLE, kanbanSearch.getTitle());
    }

    @Test
    public void modifyWithExistIdShouldModifyKanban() {

        KanbanDto dto = new KanbanDto();
        dto.setTitle(MODIFY_KANBAN_TITLE);
        dto.setDescription(NEW_KANBAN_DESCRIPTION);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH_SAVE + MODIFY_KANBAN_ID + "/" + EXIST_USER_ID,
                HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<KanbanDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_USER_ID, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(2, response.getBody().size());

        KanbanDto kanbanSearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_KANBAN_ID))
                .findFirst().orElse(null);
        assertNotNull(kanbanSearch);
        assertEquals(MODIFY_KANBAN_TITLE, kanbanSearch.getTitle());
    }

    @Test
    public void deleteWithExistsIdShouldDeleteKanban() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_KANBAN_ID, HttpMethod.DELETE, null, Void.class);

        ResponseEntity<List<KanbanDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_USER_ID, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }
}
