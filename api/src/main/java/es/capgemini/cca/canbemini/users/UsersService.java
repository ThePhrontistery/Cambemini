package es.capgemini.cca.canbemini.users;

import java.util.List;

import es.capgemini.cca.canbemini.security.UserDetailsImpl;

//es una interface que define un conjunto de métodos
public interface UsersService {
    List<Users> findAll(); // devuelve una lista de todos los objetos Users

    Users findUsers(Long id); // devuelve un Usuario por id

    void deleteUsers(Long id); // borra usuario por id

    void saveUsers(Long id, UsersDto usersDto); // guarda el objeto Users con el ID especificado y la información
                                                // contenida en usersDto

    Users findByEmail(String email); // devuelve un usuario por email

    public Boolean verifyUser(Long userId, UserDetailsImpl userDetailsImpl); // verifica si el usuario con el ID
                                                                             // especificado
}
