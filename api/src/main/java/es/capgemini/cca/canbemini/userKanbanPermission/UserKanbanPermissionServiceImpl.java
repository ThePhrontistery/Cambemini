package es.capgemini.cca.canbemini.userKanbanPermission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserKanbanPermissionServiceImpl implements UserKanbanPermissionService {

    @Autowired
    UserKanbanPermissionRepository userKanbanPermissionRepository;

    @Override
    public List<UserKanbanPermission> get() {
        return (List<UserKanbanPermission>) this.userKanbanPermissionRepository.findAll();
    }

}
