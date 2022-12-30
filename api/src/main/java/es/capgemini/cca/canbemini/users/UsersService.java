package es.capgemini.cca.canbemini.users;

import java.util.List;

import es.capgemini.cca.canbemini.security.UserDetailsImpl;

public interface UsersService {
    List<Users> findAll();

    Users findUsers(Long id);

    void deleteUsers(Long id);

    void saveUsers(Long id, UsersDto usersDto);

    Users findByEmail(String email);

    public Boolean verifyUser(Long userId, UserDetailsImpl userDetailsImpl);
}
