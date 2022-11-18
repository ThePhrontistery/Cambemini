export class Kanban {
    id: number;
    title: string;
    swimlanes: Lane[];
}

export class Lane {
    id:number;
    title: string;
    order:number;
    items:Task[];
    
}
export class Task {
    title:string;
    description:string;
    id:number;       
}
