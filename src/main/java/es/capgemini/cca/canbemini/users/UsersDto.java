package es.capgemini.cca.canbemini.users;

import java.util.Set;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.permission.Permission;

public class UsersDto {
    private long id;

    private Permission permission;

    private Set<Kanban> kanban;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Set<Kanban> getKanban() {
        return kanban;
    }

    public void setKanban(Set<Kanban> kanban) {
        this.kanban = kanban;
    }

}
