package es.capgemini.cca.canbemini.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.PermissionMapper;

@RequestMapping(value = "/api/permission")
@RestController
@CrossOrigin(origins = "*")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @Autowired
    PermissionMapper permissionMapper;

    public PermissionController() {

    }

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public PermissionDto getPermission(@PathVariable("id") Long id) {
        return permissionMapper.PermissionToPermissionDto(permissionService.findPermission(id));
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<PermissionDto> getAllPermission() {
        return permissionMapper.map(permissionService.findAll());
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody PermissionDto permissionDto,
            @PathVariable(name = "userId") Long userId) {
        permissionService.savePermission(id, permissionDto);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        permissionService.deletePermission(id);
    }

}
