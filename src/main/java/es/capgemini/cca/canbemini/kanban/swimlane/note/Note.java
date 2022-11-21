package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;

@Entity

@Table(name = "Note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany
    @JoinColumn(name = "attachment_id")
    private Set<Attachment> attachment;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Attachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(Set<Attachment> attachment) {
        this.attachment = attachment;
    }

}
