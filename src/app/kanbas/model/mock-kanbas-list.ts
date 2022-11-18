import { Lane } from "./Kanbas";

export const KANBAS_DATA_LIST : Lane[] = [
    { id: 1,title:"To do",color:'',order:0,tasks:[{id:1,title:"Cloud design",description:"Design of our cloud-based backend",LaneId:1},{id:2,title:"Client tasks usability",description:"Think and design how clients will interact with notes at the same time", LaneId:1}]},
    { id: 2,title:"To progress",color:'',order:0,tasks:[{id:3,title:"UI implementation",description:"Implementation of our Angular 14 Kanva UI",LaneId:2}]},
    { id: 3,title:"Done", color:'',order:0,tasks:[{id:4,title:"UI Design",description:"Design of our UI to show the partner",LaneId:3}]}    
]  
