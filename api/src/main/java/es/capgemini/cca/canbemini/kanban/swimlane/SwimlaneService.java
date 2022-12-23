package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;

public interface SwimlaneService {

    List<Swimlane> findAll(Long id);

    Swimlane findSwimlane(Long id);

    void deleteSwimlane(Long id);

    Swimlane saveSwimlane(Long id, SwimlaneDto swimlaneDto, Long kanbanId);

    boolean isAuthorized(String permission, Long swimlaneId) throws NotAuthorizedException;
}
