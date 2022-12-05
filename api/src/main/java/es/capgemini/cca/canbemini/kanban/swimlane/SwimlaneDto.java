package es.capgemini.cca.canbemini.kanban.swimlane;

import es.capgemini.cca.canbemini.kanban.KanbanDto;

public class SwimlaneDto {
    private Long id;

    private String title;

    private KanbanDto kanban;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public KanbanDto getKanban() {
        return kanban;
    }

    public void setKanban(KanbanDto kanban) {
        this.kanban = kanban;
    }

}
