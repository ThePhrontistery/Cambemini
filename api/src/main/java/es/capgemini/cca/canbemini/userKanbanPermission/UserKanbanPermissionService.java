package es.capgemini.cca.canbemini.userKanbanPermission;

import java.util.List;

public interface UserKanbanPermissionService {

    public List<UserKanbanPermission> get();

    // para cuando un usuario crea un kanban nuevo
    public void saveUkp(Long id, Long userId, Long kanbanId, Long permissionId);

    public UserKanbanPermission getUkp(Long id);

    // para cuando se añade un usuario a un kanban ya existente
    public void newUserInKanban(Long userId, Long kanbanId, Long permissionId);
}
