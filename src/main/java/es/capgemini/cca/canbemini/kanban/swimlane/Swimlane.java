package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;

@Entity
@Table(name = "swimlane")
public class Swimlane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "kanban_id")
    private Kanban kanban;

    @OneToMany(mappedBy = "swimlane")
    private Set<Note> notes;

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

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

}
