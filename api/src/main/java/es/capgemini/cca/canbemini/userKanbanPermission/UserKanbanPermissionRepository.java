package es.capgemini.cca.canbemini.userKanbanPermission;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserKanbanPermissionRepository extends CrudRepository<UserKanbanPermission, Long> {

    @Query("select ukp from UserKanbanPermission ukp where ukp.users.id = :userId AND ukp.kanban.id = :kanbanId")
    UserKanbanPermission findByUserIdAndKanbanCode(Long userId, Long kanbanId);
}
