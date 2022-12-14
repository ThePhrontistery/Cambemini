package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

public interface SwimlaneService {

    List<Swimlane> findAll(Long id);

    Swimlane findSwimlane(Long id);

    void deleteSwimlane(Long id);

    Swimlane saveSwimlane(Long id, SwimlaneDto swimlaneDto, Long kanbanId);
}
