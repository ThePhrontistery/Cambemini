package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

public interface AttachmentService {

    List<Attachment> findAll();

    Attachment findAttachment(Long id);

    void deleteAttachment(Long id);
}
