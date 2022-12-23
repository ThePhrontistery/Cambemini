package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.NoteMapper;

@RequestMapping(value = "/api/kanban/swimlane/note")
@RestController
@CrossOrigin(origins = "*")
public class NoteController {
    @Autowired
    NoteService noteService;

    @Autowired
    NoteMapper noteMapper;

    public NoteController() {

    }

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public NoteDto getNote(@PathVariable("id") Long id) {
        return noteMapper.noteToNoteDto(noteService.getNote(id));
    }

    @RequestMapping(path = "/{swimlaneId}", method = RequestMethod.GET)
    public List<NoteDto> getAllNotes(@PathVariable("swimlaneId") Long swimlaneId) {
        return noteMapper.map(noteService.findAllSwimlaneNotes(swimlaneId));
    }

    @RequestMapping(path = { "/save/{swimlaneId}", "/save/{id}/{swimlaneId}" }, method = RequestMethod.PUT)
    public Note save(@PathVariable(name = "id", required = false) Long id,
            @PathVariable(name = "swimlaneId", required = true) Long swimlaneId, @RequestBody NoteDto note) {
        return noteService.saveNote(id, note, swimlaneId);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
    }

    @RequestMapping(path = "/updateNotesOrder/{swimlaneId}", method = RequestMethod.PUT)
    public void updateNotesOrder(@RequestBody List<NoteDto> notes, @PathVariable("swimlaneId") Long swimlaneId) {
        for (int i = 0; i < notes.size(); i++)
            noteService.saveNote(notes.get(i).getId(), notes.get(i), swimlaneId);
    }
}
