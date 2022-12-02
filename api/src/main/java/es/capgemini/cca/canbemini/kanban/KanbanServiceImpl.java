package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionService;

@Service
public class KanbanServiceImpl implements KanbanService {

    @Autowired
    KanbanRepository kanbanRepository;

    @Autowired
    UserKanbanPermissionService ukpService;

    @Override
    public List<Kanban> findUserKanbans(Long userId) {
        return (List<Kanban>) this.kanbanRepository.findUserKanbans(userId);
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
    public void saveKanban(Long id, KanbanDto kanbanDto, Long userId) {
        Kanban kanban = null;
        if (id == null)
            kanban = new Kanban();
        else
            kanban = this.getKanban(id);

        kanban.setTitle(kanbanDto.getTitle());
        kanban.setDescription(kanbanDto.getDescription());

        this.ukpService.saveUkp(null, userId, kanbanDto, 1L);

        this.kanbanRepository.save(kanban);

    }

}
