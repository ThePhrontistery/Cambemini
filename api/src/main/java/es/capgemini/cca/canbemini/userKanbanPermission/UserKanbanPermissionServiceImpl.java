package es.capgemini.cca.canbemini.userKanbanPermission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.KanbanDto;
import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.permission.PermissionService;
import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersService;

@Service
public class UserKanbanPermissionServiceImpl implements UserKanbanPermissionService {

    @Autowired
    UserKanbanPermissionRepository userKanbanPermissionRepository;

    @Autowired
    UsersService userService;

    @Autowired
    PermissionService permissionService;

    @Override
    public List<UserKanbanPermission> get() {
        return (List<UserKanbanPermission>) this.userKanbanPermissionRepository.findAll();
    }

    @Override
    public void saveUkp(Long id, Long userId, KanbanDto kanbanDto, Long permissionId) {

        UserKanbanPermission ukp = null;

        if (id == null)
            ukp = new UserKanbanPermission();
        else {
            // falta editar
        }

        Users user = userService.findUsers(userId);
        Permission permission = permissionService.findPermission(permissionId);
        Kanban kanban = new Kanban(kanbanDto.getTitle(), kanbanDto.getDescription());
        ukp.setKanban(kanban);
        ukp.setPermission(permission);
        ukp.setUsers(user);

        this.userKanbanPermissionRepository.save(ukp);
    }

    @Override
    public void addUserToUkp(Long id, Long userId, Long kanbanId, Long permissionId) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserKanbanPermission getUkp(Long id) {
        return userKanbanPermissionRepository.findById(id).orElse(null);
    }
}
