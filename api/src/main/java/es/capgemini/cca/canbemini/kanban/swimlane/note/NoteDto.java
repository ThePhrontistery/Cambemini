package es.capgemini.cca.canbemini.kanban.swimlane.note;

<<<<<<< HEAD
import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
=======
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;
>>>>>>> b5077b06807a277894ef06259901b75b5ad2b72c

public class NoteDto {

    private Long id;

    private String content;

    private Set<Attachment> attachment;

<<<<<<< HEAD
=======
    @JsonIgnore
>>>>>>> b5077b06807a277894ef06259901b75b5ad2b72c
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

<<<<<<< HEAD
    public Swimlane getSwimlane() {
        return swimlane;
    }

    public void setSwimlane(Swimlane swimlane) {
        this.swimlane = swimlane;
=======
    public Set<Attachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(Set<Attachment> attachment) {
        this.attachment = attachment;
>>>>>>> b5077b06807a277894ef06259901b75b5ad2b72c
    }

    public Swimlane getSwimlane() {
        return swimlane;
    }

    public void setSwimlane(Swimlane swimlane) {
        this.swimlane = swimlane;
    }

}