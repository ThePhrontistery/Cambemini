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
  emitDeleteCard: EventEmitter<any> = new EventEmitter();
  emitAddCard: EventEmitter<Notes> = new EventEmitter();
  emitAddKanba: EventEmitter<Lane> = new EventEmitter();
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
    let url = this.url+"/"+userId ;
    return  this.httpClient.get<Kanban[]>(url);
  }

  saveKanban(kanban:Kanban):Observable<Kanban> {
    
    let url = this.url;
    if (kanban.id != null) url += '/'+kanban.id;
     return this.httpClient.put<Kanban>(url, kanban);
  }
 
  removeKanban(kanban:Kanban):Observable<any> {
    
    let url = this.url;
    if (kanban.id != null) url += '/'+kanban.id;
    return this.httpClient.delete(url);
 
  }

  saveItem(entitie:Notes){
    this.emitAddCard.emit(entitie);
  }
  
  saveSwimlane(lane:any){
    let url = this.url+"/swimlane";
    if (lane.id != null) url += '/'+lane.id;
    return this.httpClient.put(url, lane);
  }

  removeLane(lane:Lane){
    this.emitRemoveLane.emit(lane);
    return true;
  }

}
