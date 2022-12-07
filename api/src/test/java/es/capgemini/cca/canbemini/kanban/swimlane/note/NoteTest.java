package es.capgemini.cca.canbemini.kanban.swimlane.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NoteTest {
    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteServiceImpl noteService;

    private static final Long EXISTS_NOTE_ID = 2L;
    private static final String NOTE_CONTENT = "New Content";

    @Test
    public void findAllShouldReturnAllNotes() {
        List<Note> list = new ArrayList<>();
        list.add(mock(Note.class));

        when(noteRepository.findAllSwimlaneNotes(EXISTS_NOTE_ID)).thenReturn(list);

        List<Note> notes = noteService.findAllSwimlaneNotes(2L);

        assertNotNull(notes);
        assertEquals(1, notes.size());
    }

    @Test
    public void deleteExistsNoteShouldDelete() {
        this.noteService.deleteNote(EXISTS_NOTE_ID);

        verify(noteRepository).deleteById(EXISTS_NOTE_ID);
    }

    @Test
    public void findExistsNoteShouldReturnNote() {
        Note note = mock(Note.class);
        when(note.getId()).thenReturn(EXISTS_NOTE_ID);
        when(noteRepository.findById(EXISTS_NOTE_ID)).thenReturn(Optional.of(note));

        Note noteResponse = noteService.getNote(EXISTS_NOTE_ID);

        assertNotNull(noteResponse);
        assertEquals(EXISTS_NOTE_ID, note.getId());
    }

    @Test
    public void saveNotExistsNoteIdShouldInsert() {

        NoteDto noteDto = new NoteDto();
        noteDto.setContent(NOTE_CONTENT);

        ArgumentCaptor<Note> note = ArgumentCaptor.forClass(Note.class);

        noteService.saveNote(null, noteDto);

        verify(noteRepository).save(note.capture());

        assertEquals(NOTE_CONTENT, note.getValue().getContent());
    }

    @Test
    public void saveExistsNoteIdShouldUpdate() {

        NoteDto noteDto = new NoteDto();
        noteDto.setContent(NOTE_CONTENT);

        Note note = mock(Note.class);

        when(noteRepository.findById(EXISTS_NOTE_ID)).thenReturn(Optional.of(note));

        noteService.saveNote(EXISTS_NOTE_ID, noteDto);

        verify(noteRepository).save(note);
    }
}
