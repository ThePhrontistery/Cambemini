package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;
import org.mapstruct.Mapper;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.KanbanDto;

@Mapper(componentModel = "spring")
public interface KanbanMapper {
    Kanban KanbanDtoToKanban(KanbanDto dto);

    KanbanDto KanbanToKanbanDto(Kanban kanban);

    List<KanbanDto> map(List<Kanban> kanbanList);
    List<UserKanbanPermissionDto> mapTo(List<UserKanbanPermission> userKanbanPermission);
    List<SwimlaneDto> mapSwimlaneToDto(List<Swimlane> swimlanes);
}
