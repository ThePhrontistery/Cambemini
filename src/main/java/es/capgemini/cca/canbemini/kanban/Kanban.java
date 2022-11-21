package es.capgemini.cca.canbemini.kanban;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.users.Users;

@Entity
@Table(name = "Kanban")
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "user_id", nullable = false)
    @ManyToMany
    private Set<Users> user;

    public Kanban(String title) {
        this.title = title;
    }

    protected Kanban() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Users> getUser() {
        return user;
    }

    public void setUser(Set<Users> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("Kanban[id=%d, title='%s']", id, title);
    }
}