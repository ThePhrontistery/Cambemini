import { KANBAS_DATA_LIST } from './model/mock-kanbas-list';
import {  Kanban } from './model/Kanbas';
import { Injectable, EventEmitter } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Lane } from './model/Lane';
import { Task } from './model/Task';

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
  constructor() { }

  
  getKanbas():Observable<Kanban[]> {
      return  of(KANBAS_DATA_LIST);
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
