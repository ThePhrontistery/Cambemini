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
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class NoteTest {
    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteServiceImpl noteService;

    private static final Long EXISTS_NOTE_ID = 1L;

    @Test
    public void findAllShouldReturnAllNotes() {
        List<Note> list = new ArrayList<>();
        list.add(mock(Note.class));

        when(noteRepository.findAll()).thenReturn(list);

        List<Note> notes = noteService.findAll();

        assertNotNull(notes);
        assertEquals(3, notes.size());
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

}
