package es.capgemini.cca.canbemini.users;

import java.util.Set;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;

public class UsersDto {
    private Long id;

    private String email;

    private Set<UserKanbanPermission> user_kanban_permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserKanbanPermission> getUser_kanban_permission() {
        return user_kanban_permission;
    }

    public void setUser_kanban_permission(Set<UserKanbanPermission> user_kanban_permission) {
        this.user_kanban_permission = user_kanban_permission;
    }

}
