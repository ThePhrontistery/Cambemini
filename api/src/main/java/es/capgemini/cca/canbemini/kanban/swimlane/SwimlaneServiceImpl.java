package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.kanban.KanbanService;

@Service
public class SwimlaneServiceImpl implements SwimlaneService {

    @Autowired
    SwimlaneRepository swimlaneRepository;

    @Autowired
    KanbanService kanbanService;

    @Override
    public List<Swimlane> findAll(Long kanbanId) {
        return (List<Swimlane>) this.swimlaneRepository.findAll(kanbanId);

    }

    @Override
    public void deleteSwimlane(Long id) {
        this.swimlaneRepository.deleteById(id);
    }

    @Override
    public Swimlane findSwimlane(Long id) {
        return this.swimlaneRepository.findById(id).orElse(null);
    }

    @Override
    public void saveSwimlane(Long id, Long kanbanId, SwimlaneDto swimlaneDto) {
        Swimlane swimlane = null;

        if (id == null)
            swimlane = new Swimlane();
        else
            swimlane = this.findSwimlane(id);

        BeanUtils.copyProperties(swimlaneDto, swimlane, "id", "title", "note");

        swimlane.setTitle(swimlaneDto.getTitle());
        swimlane.setKanban(kanbanService.getKanban(kanbanId));
        // swimlane.setNote(swimlaneDto.getNote());
        // swimlane.setKanban(kanbanService.getKanban(swimlaneDto.getKanban().getId()));

        this.swimlaneRepository.save(swimlane);
    }

}
