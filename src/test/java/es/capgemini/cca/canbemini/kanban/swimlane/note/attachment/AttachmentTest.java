package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AttachmentTest {

    @Mock
    AttachmentRepository attachmentRepository;

    @InjectMocks
    AttachmentServiceImpl attachmentService;

    private static final Long EXISTS_ATTACHMENT_ID = 1L;

    @Test
    public void findAllShouldReturnAllAttachments() {
        List<Attachment> list = new ArrayList<>();
        list.add(mock(Attachment.class));

        when(attachmentRepository.findAll()).thenReturn(list);

        List<Attachment> attachment = attachmentService.findAll();

        assertNotNull(attachment);
        assertEquals(1, attachment.size());
    }

    @Test
    public void deleteExistsAttachmentShouldDelete() {
        this.attachmentService.deleteAttachment(EXISTS_ATTACHMENT_ID);

        verify(attachmentRepository).deleteById(EXISTS_ATTACHMENT_ID);
    }

    @Test
    public void findExistsAttachmentShouldReturnAttachment() {
        Attachment attachment = mock(Attachment.class);
        when(attachment.getId()).thenReturn(EXISTS_ATTACHMENT_ID);
        when(attachmentRepository.findById(EXISTS_ATTACHMENT_ID)).thenReturn(Optional.of(attachment));

        Attachment attachmentResponse = attachmentService.findAttachment(EXISTS_ATTACHMENT_ID);

        assertNotNull(attachmentResponse);
        assertEquals(EXISTS_ATTACHMENT_ID, attachment.getId());
    }

}
