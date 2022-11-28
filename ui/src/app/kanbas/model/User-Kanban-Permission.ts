import { Kanban } from "./Kanbas";
import { Permission } from "./Permission";
import { User } from "./User";

export interface UserKanbanPermission {
    id: number,
    user: User,
    kanban: Kanban,
    permission: Permission
}