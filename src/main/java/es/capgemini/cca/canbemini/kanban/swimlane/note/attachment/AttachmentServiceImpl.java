package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public List<Attachment> findAll() {
        return (List<Attachment>) this.attachmentRepository.findAll();
    }

    @Override
    public Attachment findAttachment(Long id) {
        return this.attachmentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAttachment(Long id) {
        this.attachmentRepository.deleteById(id);
    }

}
