package es.capgemini.cca.canbemini.kanban;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Kanban")
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    // @OneToMany(mappedBy = "kanban", cascade = { CascadeType.ALL }, orphanRemoval
    // = true)
    // private List<UserKanbanPermission> userKanbanPermission;

    // @OneToMany(mappedBy = "kanban", orphanRemoval = true)
    // private List<Swimlane> swimlanes;

    public Kanban(String title, String description) {
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Kanban[id=%d, title='%s', description='%s']", id, title, description);
    }

}