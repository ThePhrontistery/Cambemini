package es.capgemini.cca.canbemini.kanban;

import java.util.List;
import java.util.Set;

import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;

public class KanbanDto {

    private Long id;

    private String title;

    private String description;

    private List<UserKanbanPermissionDto> userKanbanPermission;

    private List<SwimlaneDto> swimlanes;

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

    public List<UserKanbanPermissionDto> getUserKanbanPermission() {
        return userKanbanPermission;
    }

    public void setUserKanbanPermission(List<UserKanbanPermissionDto> userKanbanPermission) {
        this.userKanbanPermission = userKanbanPermission;
    }

    public List<SwimlaneDto> getSwimlanes() {
        return swimlanes;
    }

    public void setSwimlanes(List<SwimlaneDto> swimlanes) {
        this.swimlanes = swimlanes;
    }
}
