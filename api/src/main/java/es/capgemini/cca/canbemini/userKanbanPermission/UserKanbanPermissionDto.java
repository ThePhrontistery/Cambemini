package es.capgemini.cca.canbemini.userKanbanPermission;

import es.capgemini.cca.canbemini.kanban.KanbanDto;
import es.capgemini.cca.canbemini.permission.PermissionDto;
import es.capgemini.cca.canbemini.users.UsersDto;

public class UserKanbanPermissionDto {
    private Long id;

    private UsersDto usersDto;

    private KanbanDto kanbanDto;

    private PermissionDto permissionDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersDto getUsersDto() {
        return usersDto;
    }

    public void setUsersDto(UsersDto usersDto) {
        this.usersDto = usersDto;
    }

    public KanbanDto getKanbanDto() {
        return kanbanDto;
    }

    public void setKanbanDto(KanbanDto kanbanDto) {
        this.kanbanDto = kanbanDto;
    }

    public PermissionDto getPermissionDto() {
        return permissionDto;
    }

    public void setPermissionDto(PermissionDto permissionDto) {
        this.permissionDto = permissionDto;
    }
}
