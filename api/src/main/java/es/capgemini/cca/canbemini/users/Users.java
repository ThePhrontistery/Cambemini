package es.capgemini.cca.canbemini.users;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UserKanbanPermission> user_kanban_permission;

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    protected Users() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
