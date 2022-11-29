package es.capgemini.cca.canbemini.userKanbanPermission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.users.Users;

@Entity
@Table(name = "User_kanban_permission")
public class UserKanbanPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "kanban_id")
    private Kanban kanban;

    @ManyToOne
    @JoinColumn(name = "permission_id")
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
