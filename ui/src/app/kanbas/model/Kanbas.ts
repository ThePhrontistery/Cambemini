import { Lane } from './Lane';
import { User } from './User';

export class Kanban{
    id:number;
    title:string;
    description:string;
    icon:string;
    select:boolean;
    users:User[];
    code:string;
    lanes:Lane[];
};

