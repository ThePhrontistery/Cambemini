package es.capgemini.cca.canbemini.kanban;

import java.util.Set;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.user_kanban_permission.User_Kanban_Permission;

public class KanbanDto {

    private Long id;

    private String title;

    private Set<User_Kanban_Permission> user_kanban_permission;

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

    public Set<User_Kanban_Permission> getUser_kanban_permission() {
        return user_kanban_permission;
    }

    public void setUser_kanban_permission(Set<User_Kanban_Permission> user_kanban_permission) {
        this.user_kanban_permission = user_kanban_permission;
    }

    public Set<Swimlane> getSwimlanes() {
        return swimlanes;
    }

    public void setSwimlanes(Set<Swimlane> swimlanes) {
        this.swimlanes = swimlanes;
    }

}
