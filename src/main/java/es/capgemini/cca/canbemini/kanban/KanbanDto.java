package es.capgemini.cca.canbemini.kanban;

import java.util.Set;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.users.Users;

public class KanbanDto {

    private Long id;

    private String title;

    private Set<Users> user;

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

    public Set<Users> getUser() {
        return user;
    }

    public void setUser(Set<Users> user) {
        this.user = user;
    }

    public Set<Swimlane> getSwimlanes() {
        return swimlanes;
    }

    public void setSwimlanes(Set<Swimlane> swimlanes) {
        this.swimlanes = swimlanes;
    }

}
