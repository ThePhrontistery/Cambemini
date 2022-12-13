package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;
import java.util.Optional;

import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    NoteService noteService;

    @Value("${spring.server.url}")
    private  String url;

    @Value("${spring.server.port}")
    private  String port;



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
    public Attachment saveAttachment(Long noteId,Long id, MultipartFile multipartFile) {
        Attachment attachment = null;

        if (id == null)
            attachment = new Attachment();
        else
            attachment = this.findAttachment(id);

        attachment.setName(multipartFile.getOriginalFilename());
        attachment.setType(multipartFile.getContentType());
        attachment.setNote(noteService.getNote(noteId));

        try{

            byte file[] =  multipartFile.getBytes();
            if(file.length>0)attachment.setFile(file);
            this.attachmentRepository.save(attachment);
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromHttpUrl(url+":"+port+"/api/kanban/swimlane/note/attachment/files")
                    .path("/"+attachment.getId())
                    .toUriString();
            attachment.setDocument_path(fileDownloadUri);
            this.attachmentRepository.save(attachment);
            return attachment;

        }catch (Exception e){

        }
        return  null;
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(Long id) {
        Attachment attachment =  attachmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        byte[] file = attachment.getFile();


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .body(file);
    }


}
