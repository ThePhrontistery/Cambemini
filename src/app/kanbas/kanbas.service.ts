import { KANBAS_DATA_LIST } from './model/mock-kanbas-list';
import { Task, Lane, kanba } from './model/Kanbas';
import { Injectable, EventEmitter} from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KanbasService {
  
  emitDeleteCard: EventEmitter<any> = new EventEmitter();
  emitAddCard: EventEmitter<Task> = new EventEmitter();
  emitAddKanba: EventEmitter<Lane> = new EventEmitter();
  emitDeleteLane: EventEmitter<number> = new EventEmitter();
  emitKankaSelect: EventEmitter<kanba> = new EventEmitter();
  


  constructor() { }

  getKanbas():Observable<Lane[]> {
      return  of(KANBAS_DATA_LIST);
  }

  saveTask(task:Task){
    this.emitAddCard.emit(task);
  }
  
  save(lane:Lane){
    this.emitAddKanba.emit(lane);
  }
 deleteLane(index)  {
   this.emitDeleteLane.emit(index)
 }

}
