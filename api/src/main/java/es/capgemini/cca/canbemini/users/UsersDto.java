package es.capgemini.cca.canbemini.users;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;

public class UsersDto {
    private Long id;

    private String email;

    private String password;

    @JsonIgnore
    private Set<UserKanbanPermissionDto> user_kanban_permission;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserKanbanPermissionDto> getUser_kanban_permission() {
        return user_kanban_permission;
    }

    public void setUser_kanban_permission(Set<UserKanbanPermissionDto> user_kanban_permission) {
        this.user_kanban_permission = user_kanban_permission;
    }

}
