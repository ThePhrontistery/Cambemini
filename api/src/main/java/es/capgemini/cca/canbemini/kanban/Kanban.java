package es.capgemini.cca.canbemini.kanban;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;

@Entity
@Table(name = "Kanban")
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "kanban")
    private Set<UserKanbanPermission> userKanbanPermission;

    @OneToMany(mappedBy = "kanban")
    private Set<Swimlane> swimlanes;

    public Kanban(String title) {
        this.title = title;
    }

    protected Kanban() {

    }

    public Set<UserKanbanPermission> getUserKanbanPermission() {
        return userKanbanPermission;
    }

    public void setUserKanbanPermission(Set<UserKanbanPermission> userKanbanPermission) {
        this.userKanbanPermission = userKanbanPermission;
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

    @Override
    public String toString() {
        return String.format("Kanban[id=%d, title='%s']", id, title);
    }

    public Set<Swimlane> getSwimlanes() {
        return swimlanes;
    }

    public void setSwimlanes(Set<Swimlane> swimlanes) {
        this.swimlanes = swimlanes;
    }

}