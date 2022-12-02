package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;

import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.permission.PermissionDto;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission PermissionDtoToPermission(PermissionDto dto);

    PermissionDto PermissionToPermissionDto(Permission permission);

    List<PermissionDto> map(List<Permission> permissionList);
}
