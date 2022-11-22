package es.capgemini.cca.canbemini.kanban;

import java.util.List;

public interface KanbanService {

    List<Kanban> findAll();

    Kanban getKanban(Long id);

    void deleteKanban(Long id);

    void saveKanban(Kanban kanban);
}
