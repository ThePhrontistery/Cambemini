package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.KanbanDto;

@Mapper(componentModel = "spring", uses = { UserKanbanPermissionMapper.class, SwimlaneMapper.class, NoteMapper.class,
        UsersMapper.class, AttachmentMapper.class })
public interface KanbanMapper {

    Kanban KanbanDtoToKanban(KanbanDto dto);

    KanbanDto KanbanToKanbanDto(Kanban kanban);

    List<KanbanDto> KanbanListToKanbaListDto(List<Kanban> kanbanList);

    List<Kanban> KanbanListDtoToKanbaList(List<KanbanDto> kanbanListDto);

}
