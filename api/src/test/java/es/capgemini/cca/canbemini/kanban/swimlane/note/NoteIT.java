package es.capgemini.cca.canbemini.kanban.swimlane.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class NoteIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/api/kanban/swimlane/note/";
    public static final Long NEW_NOTE_ID = 2L;
    public static final Long EXIST_NOTE_ID = 4L;
    public static final String NEW_NOTE_CONTENT = "NOTE";
    public static final Long MODIFY_NOTE_ID = 3L;
    public static final Long DELETE_NOTE_ID = 2L;
    public static final Long EXIST_SWIMLANE_ID = 1L;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<NoteDto>> responseType = new ParameterizedTypeReference<List<NoteDto>>() {
    };

    @Test
    public void findAllShouldReturnAllNotes() {

        ResponseEntity<List<NoteDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_SWIMLANE_ID, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewNote() {

        NoteDto dto = new NoteDto();
        dto.setContent(NEW_NOTE_CONTENT);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<NoteDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_SWIMLANE_ID, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(10, response.getBody().size());

        NoteDto noteSearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_NOTE_ID)).findFirst()
                .orElse(null);
        assertNotNull(noteSearch);
        assertEquals(NEW_NOTE_CONTENT, noteSearch.getContent());
    }

    @Test
    public void modifyWithExistIdShouldModifyNote() {

        NoteDto dto = new NoteDto();
        dto.setContent(NEW_NOTE_CONTENT);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + MODIFY_NOTE_ID, HttpMethod.PUT, new HttpEntity<>(dto),
                Void.class);

        ResponseEntity<List<NoteDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXIST_NOTE_ID,
                HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(3, response.getBody().size());

        NoteDto noteSearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_NOTE_ID)).findFirst()
                .orElse(null);
        assertNotNull(noteSearch);
        assertEquals(NEW_NOTE_CONTENT, noteSearch.getContent());
    }

    @Test
    public void modifyWithNotExistIdShouldInternalError() {

        NoteDto dto = new NoteDto();
        dto.setContent(NEW_NOTE_CONTENT);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NEW_NOTE_ID,
                HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void deleteWithExistsIdShouldDeleteNote() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_NOTE_ID, HttpMethod.DELETE, null, Void.class);

        ResponseEntity<List<NoteDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_SWIMLANE_ID, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void deleteWithNotExistsIdShouldInternalError() {

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NEW_NOTE_ID,
                HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
