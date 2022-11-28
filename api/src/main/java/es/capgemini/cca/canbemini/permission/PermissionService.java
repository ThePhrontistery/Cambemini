package es.capgemini.cca.canbemini.permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    Permission findPermission(Long id);

    void deletePermission(Long id);

    void savePermission(Long id, PermissionDto permissionDto);
}
