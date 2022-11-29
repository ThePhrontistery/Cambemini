import { environment } from 'src/environments/environment';
import { KANBAS_DATA_LIST } from './model/mock-kanbas-list';
import {  Kanban } from './model/Kanbas';
import { Injectable, EventEmitter } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Lane } from './model/Lane';
import { Task } from './model/Task';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class KanbasService {
  emitDeleteCard: EventEmitter<any> = new EventEmitter();
  emitAddCard: EventEmitter<Task> = new EventEmitter();
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

  
  getKanban():Observable<Kanban[]> {
      return  this.httpClient.get<Kanban[]>(this.url);
  }

  saveKanban(kanban:Kanban):Observable<void> {
    
    let url = this.url;
    if (kanban.id != null) url += '/'+kanban.id;
     return this.httpClient.put<void>(url, kanban);
 
  }
 
  removeKanban(kanban:Kanban):Observable<any> {
    
    let url = this.url;
    if (kanban.id != null) url += '/'+kanban.id;
    return this.httpClient.delete(url);
 
  }

 

  saveItem(entitie:Task){
    this.emitAddCard.emit(entitie);
  }
  
  save(entitie:Lane){
    this.emitAddKanba.emit(entitie);
  }

  removeLane(lane:Lane){
    this.emitRemoveLane.emit(lane);
    return true;
  }

}
