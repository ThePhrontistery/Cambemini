package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public List<Attachment> findAttachmentNotes(Long noteId) {
        return (List<Attachment>) this.attachmentRepository.findAttachmentNotes(noteId);
    }

    @Override
    public Attachment findAttachment(Long id) {
        return this.attachmentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAttachment(Long id) {
        this.attachmentRepository.deleteById(id);
    }

    @Override
    public void saveAttachment(Long id, AttachmentDto attachmentDto) {
        Attachment attachment = null;

        if (id == null)
            attachment = new Attachment();
        else
            attachment = this.findAttachment(id);

        attachment.setDocument_path(attachmentDto.getDocument_path());

        this.attachmentRepository.save(attachment);
    }

}
