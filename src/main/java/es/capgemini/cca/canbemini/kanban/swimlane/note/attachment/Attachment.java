package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;

@Entity
@Table(name = "Attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Set<Note> note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Note> getNote() {
        return note;
    }

    public void setNote(Set<Note> note) {
        this.note = note;
    }

}