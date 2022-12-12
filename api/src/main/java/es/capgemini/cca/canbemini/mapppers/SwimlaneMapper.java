package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;

@Mapper(componentModel = "spring", uses = { UserKanbanPermissionMapper.class, PermissionMapper.class, UsersMapper.class,
        AttachmentMapper.class, NoteMapper.class })
public interface SwimlaneMapper {
    @Mapping(target = "kanban", ignore = true)
    Swimlane SwimlaneDtoToSwimlane(SwimlaneDto dto);

    @Mapping(target = "kanban", ignore = true)
    SwimlaneDto SwimlaneToSwimlaneDto(Swimlane swimlane);

    List<Swimlane> swimlaneListDtoToSwimlaneList(List<SwimlaneDto> swimlaneListDto);

    List<SwimlaneDto> swimlaneListToSwimlaneListDto(List<Swimlane> swimlaneList);
}
