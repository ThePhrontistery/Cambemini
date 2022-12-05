
import { Kanban } from './Kanban';
import { Lane } from "./Lane";
import { User } from './User';



export const LANE_DATA_LIST: Lane[] = [
  {
    id: 1,
    title: 'To do',
    
  },
  {
    id: 2,
    title: 'To progres',
  },
  {
    id: 3,
    title: 'Done',
    
  },
  {
    id: 4,
    title: 'Deploy',
  },
];

export const KANBAS_DATA_LIST: Kanban[] = [
  {
    id: 1,
    title: 'Escape',
    description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
    select: true,
  },
  {
    id: 2,
    title: 'Site',
    description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
    select: false,
  },
];

export  const USER:User ={
  id:1,
  email:'mercedes@escape.com',
  online:false
}
