import { Title } from '@angular/platform-browser';
export class Lane {
    id:number;
    title: string;
    order:number;
    tasks:Task[];
    color:string;
    
}

export class Task {
    title:string;
    description:string;
    LaneId:number; 
    id:number;           
}

export class kanba{
    id:number;
    title:string;
    icon:string;
    select:boolean;
    users:User[];
}

export class User{
    email:string;
    online:boolean;
}