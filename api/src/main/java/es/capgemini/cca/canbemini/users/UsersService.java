package es.capgemini.cca.canbemini.users;

import java.util.List;

import es.capgemini.cca.canbemini.security.UserDetailsImpl;

public interface UsersService {
    List<Users> findAll();

    Users findUsers(Long id);

    public Users findUsersByEmail(String email);

    void deleteUsers(Long id);

    void saveUsers(Long id, UsersDto usersDto);

    public Boolean verifyUser(Long userId, UserDetailsImpl userDetailsImpl);
}
