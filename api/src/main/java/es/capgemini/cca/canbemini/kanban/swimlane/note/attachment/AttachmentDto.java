package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;

public class AttachmentDto {

    private Long id;

    private String document_path;

    @JsonIgnore
    private Note note;

    public AttachmentDto(Note note, String document_path) {
        this.note = note;
        this.document_path = document_path;
    }

    protected AttachmentDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument_path() {
        return document_path;
    }

    public void setDocument_path(String document_path) {
        this.document_path = document_path;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

}
