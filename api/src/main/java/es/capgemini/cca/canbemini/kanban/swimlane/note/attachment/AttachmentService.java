package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {

    List<Attachment> findAttachmentNotes(Long noteId);

    Attachment findAttachment(Long id);

    void deleteAttachment(Long id);

    Attachment saveAttachment(Long noteId, Long id, MultipartFile multipartFile);

    ResponseEntity<byte[]> downloadFile(Long id);
}
