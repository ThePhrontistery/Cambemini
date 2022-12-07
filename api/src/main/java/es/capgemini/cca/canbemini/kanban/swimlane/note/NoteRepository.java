package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends CrudRepository<Note, Long> {
    // List<Note> findAll(Long id);
    @Query("select n from Note n where (n.swimlane.id = :swimlaneId)")
    List<Note> findAllSwimlaneNotes(@Param("swimlaneId") Long swimlaneId);

}
