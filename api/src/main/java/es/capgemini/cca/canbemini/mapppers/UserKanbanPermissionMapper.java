package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;

@Mapper(componentModel = "spring")
public interface UserKanbanPermissionMapper {
    List<UserKanbanPermissionDto> map(List<UserKanbanPermission> kanbanList);
}
