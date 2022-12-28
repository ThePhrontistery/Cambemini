package es.capgemini.cca.canbemini.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {

<<<<<<< HEAD
    public Optional<Users> findByEmail(String email);

=======
    Optional<Users> findByEmail(String email);
>>>>>>> origin/future
}
