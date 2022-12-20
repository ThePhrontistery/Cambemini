package es.capgemini.cca.canbemini.kanban;

import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;

@Entity
@Table(name = "Kanban")
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "kanban", orphanRemoval = true)
    private List<UserKanbanPermission> userKanbanPermission;

    @OneToMany(mappedBy = "kanban", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Swimlane> swimlanes;

    public Kanban(String title, String description) {
        this.title = title;
        this.description = description;
        this.code = this.generateCode();
    }

    protected Kanban() {

    }

    public List<UserKanbanPermission> getUserKanbanPermission() {
        return userKanbanPermission;
    }

    public void setUserKanbanPermission(List<UserKanbanPermission> userKanbanPermission) {
        this.userKanbanPermission = userKanbanPermission;
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

    @Override
    public String toString() {
        return String.format("Kanban[id=%d, title='%s', description='%s']", id, title, description);
    }

    public List<Swimlane> getSwimlanes() {
        return swimlanes;
    }

    public void setSwimlanes(List<Swimlane> swimlanes) {
        this.swimlanes = swimlanes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String generateCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 25;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        return generatedString;
    }
}