package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteService;
import es.capgemini.cca.canbemini.mapppers.AttachmentMapper;

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
        // return attachmentMapper.map(attachmentService.findAttachmentNotes(noteId));
    }

    @RequestMapping(path = { "/{noteId}", "/{id}/{noteId}" }, method = RequestMethod.PUT)
    public Attachment save(@PathVariable(name = "id", required = false) Long id,
            @PathVariable(name = "noteId", required = true) Long noteId, @RequestParam MultipartFile multipartFile) {
        return attachmentService.saveAttachment(noteId, id, multipartFile);
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
