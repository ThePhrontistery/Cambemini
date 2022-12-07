package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;

public class NoteDto {

    private Long id;

    private String content;

    private Set<Attachment> attachment;

    @JsonIgnore
    private Swimlane swimlane;

    public NoteDto(String content, Swimlane swimlane) {
        this.content = content;
        this.swimlane = swimlane;
    }

    protected NoteDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Attachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(Set<Attachment> attachment) {
        this.attachment = attachment;
    }

    public Swimlane getSwimlane() {
        return swimlane;
    }

    public void setSwimlane(Swimlane swimlane) {
        this.swimlane = swimlane;
    }

}