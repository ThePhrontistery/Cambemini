import { MatDialogRef } from '@angular/material/dialog';
import { environment } from 'src/environments/environment';
import { KANBAS_DATA_LIST } from './model/mock-kanbas-list';
import {  Kanban } from './model/Kanban';
import { Injectable, EventEmitter } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Lane } from './model/Lane';
import { Notes } from './model/Notes';
import { HttpClient } from '@angular/common/http';
import { UserKanbanPermission } from './model/User-Kanban-Permission';

@Injectable({
  providedIn: 'root'
})
export class KanbasService {
  
  emitKankaSelect: EventEmitter<Kanban> = new EventEmitter();
  emitSaveKanba: EventEmitter<Kanban> = new EventEmitter();
  emitRemoveLane: EventEmitter<Lane> = new EventEmitter();
  
  kanba:Kanban;
  kanbas:Kanban[];
  url = environment.url+"kanban";
  constructor(
    private httpClient:HttpClient
  ) { }

  
  getKanbans(userId:number):Observable<Kanban[]> {
    let url = this.url+"/"+userId  ;
    return  this.httpClient.get<Kanban[]>(url);
  }

  getKanban(userId:number, kanbanId:number):Observable<Kanban[]> {
    //Adicionar al url el kanbanId a la peticion 
    let url = this.url+"/"+userId+"/"+kanbanId ;
    return  this.httpClient.get<Kanban[]>(url);
  }


  saveKanban(kanban:Kanban,userId:number):Observable<Kanban> {
    
    let url = this.url+'/save';
    if (kanban.id != null) url += '/'+kanban.id;
    url+='/'+userId;
    return this.httpClient.put<Kanban>(url, kanban);
  }
 
  removeKanban(kanban:Kanban):Observable<any> {
    
    let url = this.url;
    if (kanban.id != null) url += '/'+kanban.id;
    return this.httpClient.delete(url);
 
  }

 
  
  saveSwimlane(lane:any){
    let url = this.url+"/swimlane";
    if (lane.id != null) url += '/'+lane.id;
    return this.httpClient.put(url, lane);
  }

  removeLane(lane:Lane){
    let url = this.url;
    if (lane.id != null) url += '/swimlane/'+lane.id;
    return this.httpClient.delete(url);
  }

  saveNote(note:Notes):Observable<Notes>{
    
    let url = this.url+"/swimlane/note/save";
    if (note.id != null) url += '/'+note.id;
    url+='/'+note.swimlane.id;
    return this.httpClient.put<Notes>(url, note);
  }

  removeNote(note:Notes){
    
    let url = this.url+"/swimlane/note";
    if (note.id != null) url += '/'+note.id;
    return this.httpClient.delete(url);
  }

}
