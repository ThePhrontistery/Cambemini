package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.Set;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;

public class SwimlaneDto {
    private Long id;

    private Kanban kanban;

    private Set<Note> notes;

    private String title;

    public SwimlaneDto(String title) {
        this.title = title;
    }

    protected SwimlaneDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
