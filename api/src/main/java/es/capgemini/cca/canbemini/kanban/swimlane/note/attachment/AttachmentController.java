package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.io.File;
import java.util.List;

import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.capgemini.cca.canbemini.mapppers.AttachmentMapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping(value = "/api/kanban/swimlane/note/attachment")
@RestController
@CrossOrigin(origins = "*")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    NoteService noteService;

    @Autowired
    AttachmentMapper attachmentMapper;

    @Autowired
    AttachmentRepository AttachmentRepository;

    public AttachmentController() {

    }

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public AttachmentDto getAttachment(@PathVariable("id") Long id) {
        return attachmentMapper.AttachmentToAttachmentDto(attachmentService.findAttachment(id));
    }

    @RequestMapping(path = "/{noteId}", method = RequestMethod.GET)
    public List<Attachment> getAllAttachmentNotes(@PathVariable("noteId") Long noteId) {
        return attachmentService.findAttachmentNotes(noteId);
        //return attachmentMapper.map(attachmentService.findAttachmentNotes(noteId));
    }

    @RequestMapping(path = { "/{noteId}", "/{id}/{noteId}" }, method = RequestMethod.PUT)
    public Attachment save(@PathVariable(name = "id", required = false) Long id, @PathVariable(name = "noteId", required = true) Long noteId,@RequestParam MultipartFile multipartFile) {
        return attachmentService.saveAttachment(noteId,id,multipartFile);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        attachmentService.deleteAttachment(id);
    }

    @GetMapping(value = "/files/{fileId}")
    ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        return attachmentService.downloadFile(fileId);
    }


}
