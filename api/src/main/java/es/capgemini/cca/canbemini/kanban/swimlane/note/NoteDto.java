package es.capgemini.cca.canbemini.kanban.swimlane.note;

import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;

public class NoteDto {

    private Long id;

    private String content;

    // private List<Attachment> attachment;

    private SwimlaneDto swimlaneDto;

    public NoteDto(String content) {
        this.content = content;

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

    public SwimlaneDto getSwimlaneDto() {
        return swimlaneDto;
    }

    public void setSwimlaneDto(SwimlaneDto swimlaneDto) {
        this.swimlaneDto = swimlaneDto;
    }

}
