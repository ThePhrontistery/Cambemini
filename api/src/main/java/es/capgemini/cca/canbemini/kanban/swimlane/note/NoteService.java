package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

public interface NoteService {

    List<Note> findAllSwimlaneNotes(Long swimlaneId);

    Note getNote(Long id);

    void deleteNote(Long id);

    void saveNote(Long id, NoteDto noteDto);
}
