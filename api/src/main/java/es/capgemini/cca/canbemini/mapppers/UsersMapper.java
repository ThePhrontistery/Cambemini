package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersDto;

public interface UsersMapper {
    Users UsersDtoToUsers(UsersDto dto);

    UsersDto UsersToUsersDto(Users users);

    List<UsersDto> map(List<Users> usersList);

}
