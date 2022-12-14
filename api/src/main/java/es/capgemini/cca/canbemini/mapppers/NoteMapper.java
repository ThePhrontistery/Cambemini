package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;
import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteDto;

@Mapper(componentModel = "spring", uses = { PermissionMapper.class, UsersMapper.class, AttachmentMapper.class })
public interface NoteMapper {

    @Mapping(target = "swimlane", ignore = true)
    Note noteDtoToNote(NoteDto dto);

    @Mapping(target = "swimlane", ignore = true)
    NoteDto noteToNoteDto(Note note);

    List<NoteDto> map(List<Note> noteList);

}
