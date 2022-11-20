package es.capgemini.cca.canbemini.kanban;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KanbanRepository extends CrudRepository<Kanban, Long> {

    List<Kanban> findByTitle(String title);

    Kanban findById(long id);
}