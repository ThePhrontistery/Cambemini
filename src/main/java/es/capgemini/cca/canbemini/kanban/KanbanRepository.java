package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface KanbanRepository extends CrudRepository<Kanban, Long> {

    List<Kanban> findByTitle(String title);

    // Kanban findById(Long id);
}