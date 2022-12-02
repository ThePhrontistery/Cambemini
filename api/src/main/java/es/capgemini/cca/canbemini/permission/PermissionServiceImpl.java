package es.capgemini.cca.canbemini.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return (List<Permission>) this.permissionRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Permission findPermission(Long id) {
        return this.permissionRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePermission(Long id) {
        this.permissionRepository.deleteById(id);

    }

    @Override
    public void savePermission(Long id, PermissionDto permissionDto) {
        Permission permission = null;

        if (id == null)
            permission = new Permission();
        else
            permission = this.findPermission(id);

        permission.setRol(permissionDto.getRol());

        this.permissionRepository.save(permission);

    }

}
