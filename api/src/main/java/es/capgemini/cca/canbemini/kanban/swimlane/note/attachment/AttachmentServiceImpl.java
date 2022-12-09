package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    NoteService noteService;

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
    public void saveAttachment(Long noteId,Long id, MultipartFile multipartFile) {
        Attachment attachment = null;

        if (id == null)
            attachment = new Attachment();
        else
            attachment = this.findAttachment(id);

        attachment.setDocument_path(multipartFile.getOriginalFilename());
        attachment.setNote(noteService.getNote(noteId));

        try{

            byte file[] =  multipartFile.getBytes();
            if(file.length>0)attachment.setFile(file);
            this.attachmentRepository.save(attachment);

        }catch (Exception e){

        }

    }

}
