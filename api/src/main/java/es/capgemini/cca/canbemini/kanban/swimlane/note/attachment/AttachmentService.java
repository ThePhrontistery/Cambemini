package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

public interface AttachmentService {

    List<Attachment> findAttachmentNotes(Long noteId);

    Attachment findAttachment(Long id);

    void deleteAttachment(Long id);

    void saveAttachment(Long id, AttachmentDto attachmentDto);
}
