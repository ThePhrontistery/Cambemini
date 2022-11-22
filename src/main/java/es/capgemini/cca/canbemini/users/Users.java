package es.capgemini.cca.canbemini.users;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.permission.Permission;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JoinColumn(name = "permission_id", nullable = false)
    @OneToOne
    private Permission permission;

    @ManyToMany
    @JoinTable(name = "user_kanban", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "kanban_id"))
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
