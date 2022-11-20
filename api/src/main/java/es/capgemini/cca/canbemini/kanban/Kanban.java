package es.capgemini.cca.canbemini.kanban;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kanban {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String title;

    protected Kanban() {}
    public Kanban(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format(
                "Kanban[id=%d, title='%s']",
                id, title);
    }
}