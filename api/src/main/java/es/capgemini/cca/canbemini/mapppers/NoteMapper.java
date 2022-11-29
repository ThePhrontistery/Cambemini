package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;

import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;
import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteDto;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    Note NoteDtoToNote(NoteDto dto);

    NoteDto NoteToNoteDto(Note note);

    List<NoteDto> map(List<Note> noteList);
}
