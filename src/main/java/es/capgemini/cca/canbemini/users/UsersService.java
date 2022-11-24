package es.capgemini.cca.canbemini.users;

import java.util.List;

public interface UsersService {
    List<Users> findAll();

    Users findUsers(Long id);

    void deleteUsers(Long id);

    void saveUsers(Long id, UsersDto usersDto);
}
