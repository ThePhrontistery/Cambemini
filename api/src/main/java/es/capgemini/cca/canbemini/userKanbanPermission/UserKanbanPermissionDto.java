package es.capgemini.cca.canbemini.userKanbanPermission;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.users.Users;

public class UserKanbanPermissionDto {
    private Long id;

    private Users users;

    private Kanban kanban;

    private Permission permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
