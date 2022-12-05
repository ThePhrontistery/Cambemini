package es.capgemini.cca.canbemini.kanban;

public class KanbanDto {

    private Long id;

    private String title;

    private String description;

    // private List<UserKanbanPermissionDto> userKanbanPermission;

    // private List<SwimlaneDto> swimlanes;

    public KanbanDto(String title) {
        this.title = title;
    }

    protected KanbanDto() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
