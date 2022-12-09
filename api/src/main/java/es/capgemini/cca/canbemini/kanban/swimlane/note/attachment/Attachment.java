package es.capgemini.cca.canbemini.kanban.swimlane.note.attachment;

import javax.persistence.*;

import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;

@Entity
@Table(name = "Attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "document_path")
    private String document_path;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;


    @Lob
    private byte[] file;

    public Attachment(Note note, String document_path) {
        this.note = note;
        this.document_path = document_path;
    }

    protected Attachment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument_path() {
        return document_path;
    }

    public void setDocument_path(String document_path) {
        this.document_path = document_path;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public byte[] getFile() {

        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}