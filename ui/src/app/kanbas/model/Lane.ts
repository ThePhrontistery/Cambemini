import { Task } from "./Task";

export class Lane {
    id:number;
    title: string;
    order:number;
    items:Task[];
    color:string;    
}