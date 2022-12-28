package es.capgemini.cca.canbemini.kanban.swimlane;

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

public class SwimlaneIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/api/kanban/swimlane/";
    public static final String SERVICE_PATH_SAVE = "/api/kanban/swimlane/save/";

    @InjectMocks
    SwimlaneServiceImpl swimlaneService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<SwimlaneDto>> responseType = new ParameterizedTypeReference<List<SwimlaneDto>>() {
    };

    public static final Long EXIST_SWIMLANE_ID = 1L;
    public static final Long NOT_EXISTS_SWIMLANE_ID = 9L;
    public static final String NEW_SWIMLANE_TITLE = "SWIMLANE1";
    public static final Long NEW_SWIMLANE_ID = 7L;
    public static final Long MODIFY_KANBAN_ID = 1L;
    private static final Long MODIFY_SWIMLANE_ID = 1L;

    private static final Long DELETE_SWIMLANE_ID = 1L;
    private static final Long EXIST_KANBAN_ID = 2L;

    @Test
    public void findAllShouldReturnAllKanbanSwimlanes() {

        ResponseEntity<List<SwimlaneDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_KANBAN_ID, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewSwimlane() {
        SwimlaneDto swimlaneDto = new SwimlaneDto();

        swimlaneDto.setTitle(NEW_SWIMLANE_TITLE);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/save/" + EXIST_KANBAN_ID, HttpMethod.PUT,
                new HttpEntity<>(swimlaneDto), Void.class);

        ResponseEntity<List<SwimlaneDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_KANBAN_ID, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(4, response.getBody().size());

        SwimlaneDto swimlaneSearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_SWIMLANE_ID))
                .findFirst().orElse(null);
        assertNotNull(swimlaneSearch);
        assertEquals(NEW_SWIMLANE_TITLE, swimlaneSearch.getTitle());
    }

    @Test
    public void modifyWithExistIdShouldModifySwimlane() {

        SwimlaneDto swimlaneDto = new SwimlaneDto();
        swimlaneDto.setTitle(NEW_SWIMLANE_TITLE);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH_SAVE + MODIFY_KANBAN_ID + "/" + MODIFY_SWIMLANE_ID,
                HttpMethod.PUT, new HttpEntity<>(swimlaneDto), Void.class);

        ResponseEntity<List<SwimlaneDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + MODIFY_KANBAN_ID, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(3, response.getBody().size());

        SwimlaneDto swimlaneSearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_SWIMLANE_ID))
                .findFirst().orElse(null);
        assertNotNull(swimlaneSearch);
        assertEquals(NEW_SWIMLANE_TITLE, swimlaneSearch.getTitle());
    }

    @Test
    public void deleteWithExistsIdShouldDeleteSwimlane() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_SWIMLANE_ID, HttpMethod.DELETE, null,
                Void.class);

        ResponseEntity<List<SwimlaneDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_SWIMLANE_ID, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(2, response.getBody().size());
    }

}
