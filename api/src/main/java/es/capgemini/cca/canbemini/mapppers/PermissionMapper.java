package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.permission.PermissionDto;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission PermissionDtoToPermission(PermissionDto dto);

    PermissionDto PermissionToPermissionDto(Permission permission);

    @Mapping(source = "user_kanban_permission", target = "user_kanban_permission", ignore = true)
    List<PermissionDto> permissionListToPermissionListDto(List<Permission> permissionList);

}
