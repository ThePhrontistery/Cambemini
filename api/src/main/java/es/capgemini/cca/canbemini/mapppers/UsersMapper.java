package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersDto;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    Users UsersDtoToUsers(UsersDto dto);

    UsersDto UsersToUsersDto(Users users);

    @Mapping(source = "user_kanban_permission", target = "user_kanban_permission", ignore = true)
    List<UsersDto> usersListToUsersListDto(List<Users> usersList);

}
