package es.capgemini.cca.canbemini.kanban.swimlane.note;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;

import java.util.List;

@Entity

@Table(name = "Note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;


     @JsonIgnore
     @OneToMany(mappedBy = "note", orphanRemoval = true,cascade = CascadeType.PERSIST)
     private List<Attachment> attachment;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "swimlane_id")
    private Swimlane swimlane;

    public Note(String content, Swimlane swimlane) {
        this.content = content;
        this.swimlane = swimlane;
    }

    protected Note() {

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

    public Swimlane getSwimlane() {
        return swimlane;
    }

    public void setSwimlane(Swimlane swimlane) {
        this.swimlane = swimlane;
    }

}