import { Kanban } from './Kanban';

import { Permission } from "./Permission";
import { User } from "./User";

export interface UserKanbanPermission {
    id: number,
    users: User, 
    Kanban ?:Kanban,   
    permission: Permission
}