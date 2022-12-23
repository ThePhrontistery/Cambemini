package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;

public interface AttachmentService {

    List<Attachment> findAttachmentNotes(Long noteId);

    Attachment findAttachment(Long id);

    void deleteAttachment(Long id);

    Attachment saveAttachment(Long noteId, Long id, MultipartFile multipartFile);

    ResponseEntity<byte[]> downloadFile(Long id);

    public Boolean isAuthorized(String permission, Long kanbanId) throws NotAuthorizedException;
}
