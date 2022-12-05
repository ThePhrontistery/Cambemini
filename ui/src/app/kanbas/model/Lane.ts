import { Kanban } from './Kanban';
import { Notes } from './Notes';

export class Lane {
    id:number;
    title: string;   
    kanban ?:Kanban;
       
}