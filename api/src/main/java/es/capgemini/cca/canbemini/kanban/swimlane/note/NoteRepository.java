package es.capgemini.cca.canbemini.kanban.swimlane.note;

import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
    // List<Note> findAll(Long id);

}
