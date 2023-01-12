package es.capgemini.cca.canbemini.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.UsersMapper;

/*s un controlador REST en Spring que se encarga de gestionar las solicitudes
 *  HTTP relacionadas con la entidad de usuario
 */
@RequestMapping(value = "/api/users")
@RestController
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersMapper usersMapper;

    public UsersController() {

    }

    /*
     * Este método maneja las solicitudes GET a la ruta /api/users/{userId}. Toma un
     * parámetro de ruta llamado userId y devuelve un objeto UsersDto que representa
     * al usuario con ese ID.
     */
    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public UsersDto findUsers(@PathVariable("userId") Long userId) {
        return usersMapper.UsersToUsersDto(usersService.findUsers(userId));
    }

    /*
     * Este método maneja las solicitudes PUT a la ruta /api/users y
     * /api/users/{userId}. Toma un parámetro de ruta opcional llamado userId y un
     * objeto UsersDto en el cuerpo de la solicitud. Si userId es null, el método
     * crea un nuevo usuario con la información proporcionada en el objeto UsersDto.
     * Si userId no es null, el método actualiza el usuario con ese ID con la
     * información proporcionada en el objeto UsersDto.
     */
    @RequestMapping(path = { "", "/{userId}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "userId", required = false) Long userId, @RequestBody UsersDto usersDto) {
        usersService.saveUsers(userId, usersDto);
    }

    /*
     * Este método maneja las solicitudes DELETE a la ruta /api/users/{userId}. Toma
     * un parámetro de ruta llamado userId y elimina al usuario con ese ID.
     */
    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("userId") Long userId) {
        usersService.deleteUsers(userId);
    }

    /*
     * Este método maneja las solicitudes GET a la ruta /api/users. Toma un
     * parámetro de consulta llamado email y devuelve un objeto UsersDto que
     * representa al usuario con ese correo electrónico.
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public UsersDto findByEmail(@RequestParam(value = "email", required = true) String email) {
        return usersMapper.UsersToUsersDto(usersService.findByEmail(email));
    }

}