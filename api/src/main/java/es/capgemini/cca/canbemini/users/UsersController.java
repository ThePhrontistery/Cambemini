package es.capgemini.cca.canbemini.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.UsersMapper;

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

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public UsersDto findUsers(@PathVariable("id") Long id) {
        return usersMapper.UsersToUsersDto(usersService.findUsers(id));
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<UsersDto> getAllUsers() {
        return usersMapper.usersListToUsersListDto(usersService.findAll());
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody UsersDto usersDto) {
        usersService.saveUsers(id, usersDto);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        usersService.deleteUsers(id);
    }

}