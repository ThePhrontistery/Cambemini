package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface KanbanRepository extends CrudRepository<Kanban, Long> {

    List<Kanban> findByTitle(String title);

    @Query("select k from Kanban k")
    List<Kanban> findUserKanbans(@Param("userId") Long userId);
    // Kanban findById(Long id);
}