package es.capgemini.cca.canbemini.userKanbanPermission;

import es.capgemini.cca.canbemini.kanban.KanbanDto;
import es.capgemini.cca.canbemini.permission.PermissionDto;
import es.capgemini.cca.canbemini.users.UsersDto;

public class UserKanbanPermissionDto {
    private Long id;

    private UsersDto users;

    private KanbanDto kanban;

    private PermissionDto permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersDto getUsers() {
        return users;
    }

    public void setUsers(UsersDto users) {
        this.users = users;
    }

    public KanbanDto getKanban() {
        return kanban;
    }

    public void setKanban(KanbanDto kanban) {
        this.kanban = kanban;
    }

    public PermissionDto getPermission() {
        return permission;
    }

    public void setPermission(PermissionDto permission) {
        this.permission = permission;
    }
}