package es.capgemini.cca.canbemini.permission;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.user_kanban_permission.User_Kanban_Permission;

@Entity
@Table(name = "Permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rol", nullable = false, unique = true)
    private String rol;

    @OneToMany(mappedBy = "permission")
    private Set<User_Kanban_Permission> user_kanban_permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Set<User_Kanban_Permission> getUser_kanban_permission() {
        return user_kanban_permission;
    }

    public void setUser_kanban_permission(Set<User_Kanban_Permission> user_kanban_permission) {
        this.user_kanban_permission = user_kanban_permission;
    }

}
