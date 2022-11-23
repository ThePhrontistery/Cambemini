package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

public interface NoteService {

    List<Note> findAll();

    Note getNote(Long id);

    void deleteNote(Long id);

    void saveNote(Long id, NoteDto noteDto);
}
