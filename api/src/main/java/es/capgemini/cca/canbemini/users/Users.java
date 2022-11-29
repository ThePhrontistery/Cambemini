package es.capgemini.cca.canbemini.users;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "users")
    private Set<UserKanbanPermission> user_kanban_permission;

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

    public Set<UserKanbanPermission> getUser_kanban_permission() {
        return user_kanban_permission;
    }

    public void setUser_kanban_permission(Set<UserKanbanPermission> user_kanban_permission) {
        this.user_kanban_permission = user_kanban_permission;
    }

}
