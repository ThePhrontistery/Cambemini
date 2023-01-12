package es.capgemini.cca.canbemini.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.security.UserDetailsImpl;

/*es una implementación de la interface UsersService, implementa todos 
 * los métodos de la interface UsersService, proporcionando la lógica necesaria
 *  para cada uno de ellos
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    // devuelve todos los objetos Users
    @Override
    public List<Users> findAll() {
        return (List<Users>) this.usersRepository.findAll();
    }

    // devuelve usuarios por id
    @Override
    public Users findUsers(Long id) {
        return this.usersRepository.findById(id).orElse(null);
    }

    // borra usuarios
    @Override
    public void deleteUsers(Long id) {
        this.usersRepository.deleteById(id);
    }

    // devuelve usuario por email
    @Override
    public Users findByEmail(String email) {
        return this.usersRepository.findByEmail(email).orElse(null);
    }

    /*
     * se utiliza para guardar un objeto Users en la base de datos. Si el parámetro
     * id es null, se crea un nuevo objeto Users y se asigna a la variable users. Si
     * el parámetro id no es null, se utiliza el método findUsers(id) para obtener
     * el objeto Users con el ID especificado y se asigna a la variable users. se
     * establece el correo electrónico del objeto users con el valor del campo email
     * del objeto usersDto utilizando el método setEmail(). se utiliza el método
     * save() del atributo usersRepository para guardar el objeto users en la base
     * de datos. Si el objeto users es nuevo, se insertará en la base de datos. Si
     * ya existe en la base de datos, se actualizará.
     */
    @Override
    public void saveUsers(Long id, UsersDto usersDto) {
        Users users = null;

        if (id == null)
            users = new Users();
        else
            users = this.findUsers(id);

        users.setEmail(usersDto.getEmail());

        this.usersRepository.save(users);
    }

    /*
     * que verifica si el usuario con el ID especificado y la información de inicio
     * de sesión en userDetailsImpl es válido. Para hacer esto, obtiene la
     * información de autenticación actual del contexto de seguridad de Spring y
     * luego utiliza el método findByEmail() del atributo usersRepository para
     * obtener el objeto Users asociado al correo electrónico especificado en la
     * información de autenticación. Si el ID del usuario obtenido coincide con el
     * ID especificado, se devuelve true, en caso contrario, se devuelve false.
     */
    public Boolean verifyUser(Long userId, UserDetailsImpl userDetailsImpl) {
        Boolean ok = false;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users user = this.usersRepository.findByEmail(authentication.getPrincipal().toString()).orElse(null);
        Long loggedUser = user.getId();

        if (userId.equals(loggedUser)) {
            ok = true;
        }
        return ok;

    }
}