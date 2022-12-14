package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.KanbanDto;
import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteDto;

public class SwimlaneDto {
    private Long id;

    @JsonIgnore
    private KanbanDto kanban;

    private List<NoteDto> notes;

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

    public KanbanDto getKanban() {
        return kanban;
    }

    public void setKanban(KanbanDto kanban) {
        this.kanban = kanban;
    }

    public List<NoteDto> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteDto> notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
