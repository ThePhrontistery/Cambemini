package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import es.capgemini.cca.canbemini.security.NotAuthorizedException;
import es.capgemini.cca.canbemini.security.UserDetailsImpl;

public interface KanbanService {

    List<Kanban> findUserKanbans(Long userId);

    List<Kanban> findUserKanbanId(Long userId, Long kanbanId);

    Kanban getKanban(Long id);

    void deleteKanban(Long id);

    void saveKanban(Long id, KanbanDto kanbanDto, Long userId);

    Kanban getByCode(String code);

    public Boolean isAuthorized(String permission, Long kanbanId) throws NotAuthorizedException;

    public Boolean verifyUser(Long userId, UserDetailsImpl userDetailsImpl);
}
