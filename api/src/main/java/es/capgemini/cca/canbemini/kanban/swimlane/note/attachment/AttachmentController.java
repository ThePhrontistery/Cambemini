package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping(path = "/get/{attachmentId}", method = RequestMethod.GET)
    @PreAuthorize("@attachmentServiceImpl.isAuthorized('Editor',#attachmentId)")
    public AttachmentDto getAttachment(@PathVariable("attachmentId") Long attachmentId) {
        return attachmentMapper.AttachmentToAttachmentDto(attachmentService.findAttachment(attachmentId));
    }

    @RequestMapping(path = "/{noteId}", method = RequestMethod.GET)
    @PreAuthorize("@noteServiceImpl.isAuthorized('Collaborator',#noteId)")
    public List<AttachmentDto> getAllAttachmentNotes(@PathVariable("noteId") Long noteId) {
        return attachmentMapper.map(attachmentService.findAttachmentNotes(noteId));
    }

    @RequestMapping(path = { "/{noteId}", "/{noteId}/{attachmentId}" }, method = RequestMethod.PUT)
    @PreAuthorize("@noteServiceImpl.isAuthorized('Editor',#noteId)")
    public Attachment save(@PathVariable(name = "noteId", required = true) Long noteId,
            @PathVariable(name = "attachmentId", required = false) Long attachmentId,
            @RequestParam MultipartFile multipartFile) {
        return attachmentService.saveAttachment(noteId, attachmentId, multipartFile);
    }

    @RequestMapping(path = "/{attachmentId}", method = RequestMethod.DELETE)
    @PreAuthorize("@attachmentServiceImpl.isAuthorized('Owner',#attachmentId)")
    public void delete(@PathVariable("attachmentId") Long attachmentId) {
        attachmentService.deleteAttachment(attachmentId);
    }

    @GetMapping(value = "/files/{fileId}")
    @PreAuthorize("@attachmentServiceImpl.isAuthorized('Editor',#fileId)")
    ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        return attachmentService.downloadFile(fileId);
    }
}
