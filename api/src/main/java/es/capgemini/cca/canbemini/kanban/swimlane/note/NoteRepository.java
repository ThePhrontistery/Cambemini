package es.capgemini.cca.canbemini.kanban.swimlane.note;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends CrudRepository<Note, Long> {
    // List<Note> findAll(Long id);
    
    //anado ORDERBY para que las notas salgan siempre ordenadas y poder cambiarlas de posición
    @Query("select n from Note n where (n.swimlane.id = :swimlaneId)ORDER BY n.id ASC")
    List<Note> findAllSwimlaneNotes(@Param("swimlaneId") Long swimlaneId);

}
