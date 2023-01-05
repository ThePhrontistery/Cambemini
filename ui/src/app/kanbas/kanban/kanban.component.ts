import { NoteBlock } from './../model/note-block';
import { StompService } from './../websockect/stomp.service';
import { UserKanbanPermission } from './../model/User-Kanban-Permission';

import { Lane } from '../model/Lane';

import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { KanbasService } from '../kanbas.service';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { LaneEditComponent } from './lane-edit/lane-edit.component';
import { ActivatedRoute } from '@angular/router';
import { Notes } from '../model/Notes';
import { ThisReceiver } from '@angular/compiler';
import { LoginService } from 'src/app/login/login.service';
import { Permission } from '../model/Permission';
import { PermisionEditComponent } from './permision-edit/permision-edit.component';

import * as SockJS from 'sockjs-client';
import { User } from '../model/User';
@Component({
  selector: 'app-kanba-list',
  templateUrl: './kanban.component.html',
  styleUrls: ['./kanban.component.scss'],
})
export class KanbanComponent implements OnInit {
  lanes: Lane[] = [];
  kanbasListId: string[] = [];
  kanbanId:number;
  userId:number;
  permission: Permission;
  title:string;
  message:string = "";
  users:User[]=[];
  userKanbanPermissions:UserKanbanPermission[]=[];
  constructor(
    private kanbasService: KanbasService,
    private dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private loginService: LoginService,
    private stompService: StompService
  ) {
    console.log('entra');
  }

  ngOnInit(): void {
    
    
    this.loginService.user.subscribe(user => {
      if(user != null) {
        this.userId = user.id;
        this.lanes = [];
        this.kanbasListId =[];
        this.kanbanId = Number(this.activatedRoute.snapshot.params.id);
        this.userKanbanPermissions = [];
        this.users=[];
        this.getkanban();
      }
    })
    
    
    this.stompService.subscribe('/kanbans/'+this.kanbanId, (res) => {
      
      let noteBlock:NoteBlock =   JSON.parse(res.body);
       
      if(noteBlock.userId == this.userId) return;
      let index = this.lanes.findIndex(lane => lane.id==noteBlock.swimlaneId);
      if(index < 0) return;        
        
      let indexNote = this.lanes[index].notes.findIndex(note => note.id==noteBlock.noteId);
        
      if(indexNote < 0) return;
      if(noteBlock.block==true)
        this.lanes[index].notes[indexNote].usersBlock = this.users.find(user => user.id == noteBlock.userId)
        else
        this.lanes[index].notes[indexNote].usersBlock = null;
     });

    this.kanbasService.emitRemoveLane.subscribe((lane) => {
      let index = this.lanes.findIndex((xLane) => lane.id == xLane.id);
      if (index != -1) this.lanes.splice(index, 1);
    });
  }

  ngOnChanges(){
    
    this.kanbasService.emitKankaSelect.emit(null);
  }

  getkanban(){
    this.kanbasService.getKanban(this.userId,this.kanbanId).subscribe((kanban) => {
      this.title = kanban.title;
      this.kanbasService.emitKankaSelect.emit(kanban);
      this.lanes.push(...kanban.swimlanes);
      this.userKanbanPermissions.push(...kanban.userKanbanPermission);
      this.users = kanban.userKanbanPermission.flatMap(x=>x.users);
      this.lanes.forEach((e, i) => {
        this.kanbasListId.push('list' + i);
      });

      this.permission = kanban.userKanbanPermission.find(userkp => userkp.users.id == this.userId).permission;
    });
  }

  addSwimlane() {
    
    const dialogRef = this.dialog.open(LaneEditComponent, {
          data: {kanbanId:this.kanbanId, order: this.lanes.length},
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.ngOnInit();
      }
    });
    return true;
  }

  dropCard(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.lanes, event.previousIndex, event.currentIndex);
    this.lanes.forEach((lane, index) => {
      if(index != lane.order)
        lane.order = index;
    })
    this.kanbasService.updateOrderLanes(this.lanes, this.kanbanId).subscribe(result => {});
  }
  
  addNote(note:Notes){
    
    let indexLane =this.lanes.findIndex(lane => lane.id== note.swimlane.id);   
    this.lanes[indexLane].notes.push(note);
  }
  
  editNote(note:Notes){
    
    let indexLane =this.lanes.findIndex(lane => lane.id== note.swimlane.id);
    let indexNote =this.lanes[indexLane].notes.findIndex(noteRes => note.id == noteRes.id);
    this.lanes[indexLane].notes[indexNote] = note;    
    
  }
  
  saveLane(lane:Lane){
   
    if(lane.id==null){
      this.lanes.push(lane);
    }else{
      let index = this.lanes.findIndex(xLane=>xLane.id==lane.id);
      if(index >=0 )this.lanes[index]=lane;
    }

  }

  canEdit(): boolean {
    if(this.permission.rol != "Collaborator")
      return true;
    else
      return false;
  }
  
  changePermission(userKanbanPermission:UserKanbanPermission){
      const dialogRef = this.dialog.open(PermisionEditComponent, {
        data: {userKanbanPermission:userKanbanPermission, title:this.title,kanbanId:this.kanbanId},
      });

      dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          debugger
          let index = this.userKanbanPermissions.findIndex(ukp=>ukp.id==result.id);
          this.userKanbanPermissions[index] = result;
        }
      });
      
      return true;
  }



  blockNote(noteBlock:NoteBlock){
    noteBlock.userId=this.userId;
    this.stompService.sendNote(this.kanbanId,noteBlock);
    
  }
}
