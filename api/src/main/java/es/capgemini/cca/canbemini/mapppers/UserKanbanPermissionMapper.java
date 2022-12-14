package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;

@Mapper(componentModel = "spring", uses = { SwimlaneMapper.class, NoteMapper.class, PermissionMapper.class,
        UsersMapper.class, AttachmentMapper.class })

public interface UserKanbanPermissionMapper {

    @Mapping(target = "kanban", ignore = true)
    UserKanbanPermission userKanbanPermissionDtoToUserKanbanPermission(UserKanbanPermissionDto userKanbanPermissionDto);

    @Mapping(target = "kanban", ignore = true)
    UserKanbanPermissionDto userKanbanPermissionToUserKanbanPermissionDto(UserKanbanPermission userKanbanPermission);

    List<UserKanbanPermission> userKanbanPermissionListDtoToUserKanbanPermissionList(
            List<UserKanbanPermissionDto> kanbanListDto);

    List<UserKanbanPermissionDto> userKanbanPermissionListToUserKanbanPermissionListDto(
            List<UserKanbanPermission> kanbanList);
}
