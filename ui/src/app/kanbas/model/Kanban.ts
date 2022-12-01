import { UserKanbanPermission } from './User-Kanban-Permission';
import { Lane } from './Lane';
export class Kanban{
    id:number;
    title:string;
    description:string;
    
    select:boolean;
    userKanbanPermission:UserKanbanPermission[];
   
    swimlanes:Lane[];
};

