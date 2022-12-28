package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;

public interface NoteService {

    List<Note> findAllSwimlaneNotes(Long swimlaneId);

    Note getNote(Long id);

    void deleteNote(Long id);

    Note saveNote(Long id, NoteDto noteDto, Long swimlaneId);

    boolean isAuthorized(String permission, Long noteId) throws NotAuthorizedException;
}
