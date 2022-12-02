package es.capgemini.cca.canbemini.kanban;

import java.util.Set;

import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;

public class KanbanDto {

    private Long id;

    private String title;

    private String description;

    private Set<UserKanbanPermissionDto> userKanbanPermission;

    private Set<SwimlaneDto> swimlanes;

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

    public Set<UserKanbanPermissionDto> getUserKanbanPermission() {
        return userKanbanPermission;
    }

    public void setUserKanbanPermission(Set<UserKanbanPermissionDto> userKanbanPermission) {
        this.userKanbanPermission = userKanbanPermission;
    }

    public Set<SwimlaneDto> getSwimlanes() {
        return swimlanes;
    }

    public void setSwimlanes(Set<SwimlaneDto> swimlanes) {
        this.swimlanes = swimlanes;
    }

}
