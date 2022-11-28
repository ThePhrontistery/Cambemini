package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KanbanServiceImpl implements KanbanService {

    @Autowired
    KanbanRepository kanbanRepository;

    @Override
    public List<Kanban> findAll() {
        return (List<Kanban>) this.kanbanRepository.findAll();
    }

    @Override
    public Kanban getKanban(Long id) {
        return this.kanbanRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteKanban(Long id) {
        this.kanbanRepository.deleteById(id);
    }

    @Override
    public void saveKanban(Long id, KanbanDto kanbanDto) {
        Kanban kanban = null;
        if (id == null)
            kanban = new Kanban();
        else
            kanban = this.getKanban(id);

        kanban.setTitle(kanbanDto.getTitle());

        this.kanbanRepository.save(kanban);
    }

}
