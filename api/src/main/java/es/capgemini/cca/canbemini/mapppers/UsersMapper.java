package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersDto;

@Mapper(componentModel = "spring", uses = { SwimlaneMapper.class, NoteMapper.class, AttachmentMapper.class,
        PermissionMapper.class })
public interface UsersMapper {
    @Mapping(target = "user_kanban_permission", ignore = true)
    Users UsersDtoToUsers(UsersDto dto);

    @Mapping(target = "user_kanban_permission", ignore = true)
    UsersDto UsersToUsersDto(Users users);

    List<UsersDto> usersListToUsersListDto(List<Users> usersList);

    List<Users> usersListDtoToUsersList(List<UsersDto> usersListDto);

}
