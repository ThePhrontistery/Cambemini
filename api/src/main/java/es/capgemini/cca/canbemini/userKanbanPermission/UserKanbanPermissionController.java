package es.capgemini.cca.canbemini.userKanbanPermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.kanban.KanbanDto;

@RequestMapping(value = "/api/ukp/")
@RestController
@CrossOrigin(origins = "*")
public class UserKanbanPermissionController {

    @Autowired
    UserKanbanPermissionService ukpService;

   /* @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody KanbanDto kanbanDto,
            @PathVariable(name = "userId") Long userId, @PathVariable(name = "permissionId") Long permissionId) {
        ukpService.saveUkp(id, userId, kanbanDto, 1L);
    }*/
}
