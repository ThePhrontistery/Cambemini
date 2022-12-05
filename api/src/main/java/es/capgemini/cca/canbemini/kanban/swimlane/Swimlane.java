package es.capgemini.cca.canbemini.kanban.swimlane;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.Kanban;

@Entity
@Table(name = "Swimlane")
public class Swimlane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "kanban_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Kanban kanban;

    public Swimlane(String title, Kanban kanban) {
        this.title = title;
        this.kanban = kanban;
    }

    protected Swimlane() {

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

    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

    @Override
    public String toString() {
        return String.format("Swimlane[id=%d, title='%s']", id, title);
    }

}
