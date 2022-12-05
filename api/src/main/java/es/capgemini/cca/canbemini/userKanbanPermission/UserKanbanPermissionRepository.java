package es.capgemini.cca.canbemini.userKanbanPermission;

import org.springframework.data.repository.CrudRepository;

public interface UserKanbanPermissionRepository extends CrudRepository<UserKanbanPermission, Long> {

    UserKanbanPermission findTopByOrderByIdDesc();
}
