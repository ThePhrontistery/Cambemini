package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;
import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;
import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteDto;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    Note noteDtoToNote(NoteDto dto);

    NoteDto noteToNoteDto(Note note);

    SwimlaneDto swimlaneToSwimlaneDto(Swimlane swimlane);

    List<NoteDto> map(List<Note> noteList);

}
