package es.capgemini.cca.canbemini.users;

import java.util.Set;

import es.capgemini.cca.canbemini.user_kanban_permission.User_Kanban_Permission;

public class UsersDto {
    private Long id;

    private String username;

    private Set<User_Kanban_Permission> user_kanban_permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<User_Kanban_Permission> getUser_kanban_permission() {
        return user_kanban_permission;
    }

    public void setUser_kanban_permission(Set<User_Kanban_Permission> user_kanban_permission) {
        this.user_kanban_permission = user_kanban_permission;
    }

}
