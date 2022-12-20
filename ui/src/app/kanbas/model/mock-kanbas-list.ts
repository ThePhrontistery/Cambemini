
import { Kanban } from './Kanban';
import { Lane } from "./Lane";
import { User } from './User';



export const LANE_DATA_LIST: Lane[] = [
  {
    id: 1,
    title: 'To do',
    order: 1,
    notes: [
      {
        id: 1,
        order: 1,
        content: 'Cloud design',
      },
      {
        id: 2,   
        order: 2,     
        content:
          'Think and design how clients will interact with notes at the same time',
      },
    ],
  },
  {
    id: 2,
    title: 'To progres',
    order: 2,

    notes: [
      {
        id: 3,
        order: 1,
        content: 'Implementation of our Angular 14 Kanva UI',
      },
    ],
  },
  {
    id: 3,
    title: 'Done',
    order: 3,
    notes: [
      {
        id: 4,
        order: 1,
        content: 'UI Design',
        
      },
    ],
  },
  {
    id: 4,
    title: 'Deploy',
    order: 4,
    notes: [
      {
        id: 5,
        order: 1,
        content: 'UI Design',
      },
    ],
  },
];

export const KANBAS_DATA_LIST: Kanban[] = [
  {
    id: 1,
    title: 'Escape',
    description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
    select: true,
    code:"g27ceR4Z8rNCXF3YeeK73jSW0",
    userKanbanPermission:[
      {id:1, users:{id:1,email:'mercedes@escape.com',password:"hola", online:false}, permission:{id:1,rol:"Owner"}},
      {id:2, users:{id:2,email:'raul@escape.com',password:"hola", online:false}, permission:{id:2,rol:"Editor"}}
    ],
    
    swimlanes:LANE_DATA_LIST
  },
  {
    id: 2,
    title: 'Site',
    description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
    code:"g27ceR4Z8rNCXF3YeeK73jSs2",
    select: false,
    userKanbanPermission:[
      {id:3, users:{id:3,email:'cesar@escape.com',password:"hola", online:false}, permission:{id:1,rol:"Owner"}},
      {id:4, users:{id:4,email:'jax@escape.com',password:"hola", online:false}, permission:{id:2,rol:"Editor"}},
      {id:5, users:{id:5,email:'fredy@escape.com',password:"hola", online:false}, permission:{id:3,rol:"Collaborator"}}
    ],
    swimlanes:LANE_DATA_LIST
  },
];

export  const USER:User ={
  id:1,
  email:'mercedes@escape.com',
  password:"hola",
  online:false
}
