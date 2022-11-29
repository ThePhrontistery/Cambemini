import { Kanban } from './Kanbas';
import { Lane } from "./Lane";

export const LANE_DATA_LIST: Lane[] = [
  {
    id: 1,
    title: 'To do',
    color: '',
    order: 0,
    items: [
      {
        id: 1,
        title: 'Cloud design',
        description: 'Design of our cloud-based backend',
        laneId: 1,
      },
      {
        id: 2,
        title: 'Client tasks usability',
        description:
          'Think and design how clients will interact with notes at the same time',
        laneId: 1,
      },
    ],
  },
  {
    id: 2,
    title: 'To progress',
    color: '',
    order: 0,
    items: [
      {
        id: 3,
        title: 'UI implementation',
        description: 'Implementation of our Angular 14 Kanva UI',
        laneId: 2,
      },
    ],
  },
  {
    id: 3,
    title: 'Done',
    color: '',
    order: 0,
    items: [
      {
        id: 4,
        title: 'UI Design',
        description: 'Design of our UI to show the partner',
        laneId: 3,
      },
    ],
  },
];

export const LANE_DATA_LIST1: Lane[] = [
  {
    id: 1,
    title: 'To do',
    color: '',
    order: 0,
    items: [
      {
        id: 1,
        title: 'Cloud design',
        description: 'Design of our cloud-based backend',
        laneId: 1,
      },
      {
        id: 2,
        title: 'Client tasks usability',
        description:
          'Think and design how clients will interact with notes at the same time',
        laneId: 1,
      },
    ],
  },
  {
    id: 2,
    title: 'To progres',
    color: '',
    order: 0,
    items: [
      {
        id: 3,
        title: 'UI implementation',
        description: 'Implementation of our Angular 14 Kanva UI',
        laneId: 2,
      },
    ],
  },
  {
    id: 3,
    title: 'Done',
    color: '',
    order: 0,
    items: [
      {
        id: 4,
        title: 'UI Design',
        description: 'Design of our UI to show the partner',
        laneId: 3,
      },
    ],
  },
  {
    id: 4,
    title: 'Deploy',
    color: '',
    order: 0,
    items: [
      {
        id: 5,
        title: 'UI Design',
        description: 'Design of our UI to show the partner',
        laneId: 4,
      },
    ],
  },
];

export const KANBAS_DATA_LIST: Kanban[] = [
  {
    id: 1,
    title: 'Escape',
    description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',

    code: 'mural-0001',
    select: true,
    icon: 'settings_accesibility',
    users: [
      { email: 'fredy@test.com', initial: 'FHO', online: true },
      { initial: 'DAV', email: 'david@test.com', online: false },
    ],
    swimlanes:LANE_DATA_LIST
  },
  {
    id: 2,
    title: 'Site',
    description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
    code: 'mural-0002',
    select: false,
    icon: 'settings_accesibility',
    users: [
      { email: 'mercedes@test.com', initial: 'FHO', online: false },
      { email: 'raul@test.com', initial: 'RAU', online: true },
    ],
    swimlanes:LANE_DATA_LIST1
  },
];
