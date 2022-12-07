package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    AttachmentMapper attachmentMapper;

    public AttachmentController() {

    }

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public AttachmentDto getAttachment(@PathVariable("id") Long id) {
        return attachmentMapper.AttachmentToAttachmentDto(attachmentService.findAttachment(id));
    }

    @RequestMapping(path = "/{noteId}", method = RequestMethod.GET)
    public List<AttachmentDto> getAllAttachmentNotes(@PathVariable("noteId") Long noteId) {
        return attachmentMapper.map(attachmentService.findAttachmentNotes(noteId));
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody AttachmentDto attachment) {
        attachmentService.saveAttachment(id, attachment);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        attachmentService.deleteAttachment(id);
    }

}
