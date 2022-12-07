package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.permission.PermissionDto;
import es.capgemini.cca.canbemini.permission.PermissionService;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionService;
import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersDto;
import es.capgemini.cca.canbemini.users.UsersService;

@Service
public class KanbanServiceImpl implements KanbanService {

    @Autowired
    KanbanRepository kanbanRepository;

    @Autowired
    UserKanbanPermissionService ukpService;

    @Autowired
    UsersService userService;

    @Autowired
    PermissionService permissionService;

    @Override
    public List<Kanban> findUserKanbans(Long userId) {
        return (List<Kanban>) this.kanbanRepository.findUserKanbans(userId);
    }

    @Override
    public List<Kanban> findUserKanbanId(Long userId, Long kanbanId) {
        return (List<Kanban>) this.kanbanRepository.findUserKanbanId(userId, kanbanId);
    }

    @Override
    public Kanban getKanban(Long id) {
        return this.kanbanRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteKanban(Long id) {
        this.kanbanRepository.deleteById(id);
    }

    /*
     * @Override public void saveKanban(Long id, KanbanDto kanbanDto, Long userId) {
     * Kanban kanban = null; if (id == null) kanban = new Kanban(); else kanban =
     * this.getKanban(id);
     * 
     * kanban.setTitle(kanbanDto.getTitle());
     * kanban.setDescription(kanbanDto.getDescription());
     * 
     * this.ukpService.saveUkp(null, userId, kanbanDto, 1L);
     * 
     * this.kanbanRepository.save(kanban);
     * 
     * }
     */

    @Override
    public void saveKanban(Long id, KanbanDto kanbanDto, Long userId) {
        Kanban kanban = null;

        UserKanbanPermissionDto ukpDto = new UserKanbanPermissionDto();
        UsersDto userDto = new UsersDto();
        PermissionDto permissionDto = new PermissionDto();

        Users user = userService.findUsers(userId);
        Permission permission = permissionService.findPermission(1L);

        userDto.setId(userId);
        userDto.setEmail(user.getEmail());
        permissionDto.setId(1L);
        permissionDto.setRol(permission.getRol());

        if (id == null)
            kanban = new Kanban();
        else
            kanban = this.getKanban(id);

        kanban.setTitle(kanbanDto.getTitle());
        kanban.setDescription(kanbanDto.getDescription());

        kanbanDto.setId(kanban.getId());

        this.kanbanRepository.save(kanban);

        if (id == null) {
            Long kanbanId = kanban.getId();
            this.ukpService.saveUkp(null, userId, kanbanId, 1L);
        }

    }

}
