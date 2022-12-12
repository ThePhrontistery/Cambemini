package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

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

public class AttachmentIT {
    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/api/kanban/swimlane/note/attachment/";

    @InjectMocks
    AttachmentServiceImpl attachmentService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<AttachmentDto>> responseType = new ParameterizedTypeReference<List<AttachmentDto>>() {
    };

    public static final Long EXISTS_ATTACHMENT_ID = 1L;
    public static final Long NOT_EXISTS_ATTACHMENT_ID = 9L;
    public static final String NEW_ATTACHMENT_PATH = "DOC1";
    public static final Long NEW_ATTACHMENT_ID = 5L;
    private static final Long DELETE_ATTACHMENT_ID = 1L;
    private static final Long EXIST_NOTE_ID = 2L;

    @Test
    public void findAllShouldReturnAllUserAttachments() {

        ResponseEntity<List<AttachmentDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_NOTE_ID, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewAttachment() {
        AttachmentDto dto = new AttachmentDto();

        dto.setDocument_path(NEW_ATTACHMENT_PATH);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXIST_NOTE_ID, HttpMethod.PUT, new HttpEntity<>(dto),
                Void.class);

        ResponseEntity<List<AttachmentDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_NOTE_ID, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(2, response.getBody().size());

        AttachmentDto attachmentSearch = response.getBody().stream()
                .filter(item -> item.getId().equals(NEW_ATTACHMENT_ID)).findFirst().orElse(null);
        assertNotNull(attachmentSearch);
        assertEquals(NEW_ATTACHMENT_PATH, attachmentSearch.getDocument_path());
    }

    @Test
    public void deleteWithExistsIdShouldDeleteAttachment() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_ATTACHMENT_ID, HttpMethod.DELETE, null,
                Void.class);

        ResponseEntity<List<AttachmentDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + EXIST_NOTE_ID, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }
}
