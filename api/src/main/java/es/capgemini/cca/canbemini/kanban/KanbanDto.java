package es.capgemini.cca.canbemini.kanban;

import java.util.Set;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;

public class KanbanDto {

    private Long id;

    private String title;

    private String description;

    private Set<UserKanbanPermission> userKanbanPermission;

    private Set<Swimlane> swimlanes;

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

    public Set<UserKanbanPermission> getUserKanbanPermission() {
        return userKanbanPermission;
    }

    public void setUserKanbanPermission(Set<UserKanbanPermission> userKanbanPermission) {
        this.userKanbanPermission = userKanbanPermission;
    }

    public Set<Swimlane> getSwimlanes() {
        return swimlanes;
    }

    public void setSwimlanes(Set<Swimlane> swimlanes) {
        this.swimlanes = swimlanes;
    }

}
