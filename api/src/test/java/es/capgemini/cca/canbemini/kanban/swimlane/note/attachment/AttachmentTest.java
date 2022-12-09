package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

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
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class AttachmentTest {

    @Mock
    AttachmentRepository attachmentRepository;

    @InjectMocks
    AttachmentServiceImpl attachmentService;

    private static final Long EXISTS_ATTACHMENT_ID = 1L;
    public static final Long NOT_EXISTS_ATTACHMENT_ID = 9L;
    private static final Long EXISTS_NOTE_ID = 1L;
    public static final String DOCUMENT_NAME = "DOC1";

    @Test
    public void findAllShouldReturnAllAttachments() {
        List<Attachment> list = new ArrayList<>();
        list.add(mock(Attachment.class));

        when(attachmentRepository.findAttachmentNotes(EXISTS_NOTE_ID)).thenReturn(list);

        List<Attachment> attachment = attachmentService.findAttachmentNotes(EXISTS_NOTE_ID);

        assertNotNull(attachment);
        assertEquals(1, attachment.size());
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

    @Test
    public void findNotExistsAttachmentShouldReturnNull() {
        when(attachmentRepository.findById(NOT_EXISTS_ATTACHMENT_ID)).thenReturn(Optional.empty());

        Attachment attachment = attachmentService.findAttachment(NOT_EXISTS_ATTACHMENT_ID);

        assertNull(attachment);

    }

    @Test
    public void deleteExistsAttachmentShouldDelete() {
        this.attachmentService.deleteAttachment(EXISTS_ATTACHMENT_ID);

        verify(attachmentRepository).deleteById(EXISTS_ATTACHMENT_ID);
    }

    @Test
    public void saveNotExistsAttachmentIdShouldInsert() {

        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setDocument_path(DOCUMENT_NAME);

        ArgumentCaptor<Attachment> attachment = ArgumentCaptor.forClass(Attachment.class);
        MultipartFile p= null;

        attachmentService.saveAttachment(EXISTS_NOTE_ID,null, p);

        verify(attachmentRepository).save(attachment.capture());

        assertEquals(DOCUMENT_NAME, attachment.getValue().getDocument_path());
    }

    @Test
    public void saveExistsKanbanIdShouldUpdate() {

        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setDocument_path(DOCUMENT_NAME);

        Attachment attachment = mock(Attachment.class);
        MultipartFile p= null;
        when(attachmentRepository.findById(EXISTS_ATTACHMENT_ID)).thenReturn(Optional.of(attachment));

        attachmentService.saveAttachment(EXISTS_NOTE_ID, EXISTS_ATTACHMENT_ID,p);

        verify(attachmentRepository).save(attachment);
    }

}
