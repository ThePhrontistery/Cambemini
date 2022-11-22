package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

public interface SwimlaneService {

    List<Swimlane> findAll();

    Swimlane findSwimlane(Long id);

    void deleteSwimlane(Long id);
}
