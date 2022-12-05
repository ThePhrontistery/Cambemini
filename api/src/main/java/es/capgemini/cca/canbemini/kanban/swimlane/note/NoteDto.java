package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;

public class NoteDto {

    private Long id;

    private String content;

    private List<Attachment> attachment;

    private SwimlaneDto swimlaneDto;

    public NoteDto(String content, SwimlaneDto swimlaneDto) {
        this.content = content;
        this.swimlaneDto = swimlaneDto;
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

    public List<Attachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<Attachment> attachment) {
        this.attachment = attachment;
    }

    public SwimlaneDto getSwimlaneDto() {
        return swimlaneDto;
    }

    public void setSwimlaneDto(SwimlaneDto swimlaneDto) {
        this.swimlaneDto = swimlaneDto;
    }

}
