package es.capgemini.cca.canbemini.permission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.users.Users;

@Entity
@Table(name = "Permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "permission_type", nullable = false)
    private int permission;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToOne
    @JoinColumn(name = "kanban_id", nullable = false)
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

    public String toString() {
        String result = "";

        switch (permission) {
        case 1:
            result = "owner";
            break;
        case 2:
            result = "editor";
            break;
        case 3:
            result = "colaborator";
            break;
        }
        return result;

    }
}
