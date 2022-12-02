package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SwimlaneRepository extends CrudRepository<Swimlane, Long> {
    @Query("select s from Swimlane s where (s.kanban.id = :kanbanId) ORDER BY s.id ASC")
    List<Swimlane> findAll(@Param("kanbanId") Long kanbanId);
}
