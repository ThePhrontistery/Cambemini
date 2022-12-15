package es.capgemini.cca.canbemini.userKanbanPermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/ukp/")
@RestController
@CrossOrigin(origins = "*")
public class UserKanbanPermissionController {

    @Autowired
    UserKanbanPermissionService ukpService;

    @RequestMapping(path = { "{kanbanId}/{userId}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "kanbanId") Long kanbanId, @PathVariable(name = "userId") Long userId) {
        ukpService.newUserInKanban(userId, kanbanId, 3L);
    }
}
