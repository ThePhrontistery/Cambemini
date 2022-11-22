import { KANBAS_DATA_LIST } from './model/mock-kanbas-list';
import {  Kanba } from './model/Kanbas';
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
  emitKankaSelect: EventEmitter<Kanba> = new EventEmitter();
  emitSaveKanba: EventEmitter<Kanba> = new EventEmitter();
  emitRemoveLane: EventEmitter<Lane> = new EventEmitter();

  
  kanba:Kanba;
  kanbas:Kanba[];
  constructor() { }

  
  getKanbas():Observable<Kanba[]> {
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
