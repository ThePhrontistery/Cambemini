package es.capgemini.cca.canbemini.permission;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.users.Users;

public class PermissionDto {
    private Long id;

    private int permission;

    private Users user;

    private Kanban kanban;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

}
