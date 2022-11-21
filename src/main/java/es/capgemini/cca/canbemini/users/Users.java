package es.capgemini.cca.canbemini.users;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.kanban.Kanban;
//import es.capgemini.cca.canbemini.permission.Permission;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /*
     * @Column(name = "permission", nullable = false) private Permission permission;
     */

    @ManyToMany
    @JoinColumn(name = "kanban_id", nullable = false)
    private Set<Kanban> kanban;

    public Set<Kanban> getKanban() {
        return kanban;
    }

    public void setKanban(Set<Kanban> kanban) {
        this.kanban = kanban;
    }
    /*
     * public Permission getPermission() { return permission; }
     * 
     * public void setPermission(Permission permission) { this.permission =
     * permission; }
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
