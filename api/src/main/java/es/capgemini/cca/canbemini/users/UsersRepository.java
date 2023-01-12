package es.capgemini.cca.canbemini.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

//es una interface que extiende de la interface CrudRepository
public interface UsersRepository extends CrudRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
}