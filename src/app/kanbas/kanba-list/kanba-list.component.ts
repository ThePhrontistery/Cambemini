import { Title } from '@angular/platform-browser';
import { MatDialog } from '@angular/material/dialog';
import { Lane, Task } from './../model/Kanbas';
import { Component, OnInit } from '@angular/core';
import { KanbasService } from '../kanbas.service';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { LaneEditComponent } from '../lane-edit/lane-edit.component';
@Component({
  selector: 'app-kanba-list',
  templateUrl: './kanba-list.component.html',
  styleUrls: ['./kanba-list.component.scss'],
})
export class KanbaListComponent implements OnInit {
  lanes: Lane[] = [];
  kanbasListId: string[] = [];

  constructor(
    private KanbasService: KanbasService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.KanbasService.getKanbas().subscribe((kanbasList) => {
      this.lanes.push(...kanbasList);

      this.lanes.forEach((e, i) => {
        this.kanbasListId.push('list' + i);
      });
    });

    this.KanbasService.emitDeleteCard.subscribe((y) => {
      console.log('aqui elminar', y);
      this.lanes[y.KanbaIndex].tasks.splice(y.Itemindex, 1);
    });

    this.KanbasService.emitAddCard.subscribe((task: Task) => {
      let lane = this.lanes.find((x) => x.id == task.LaneId);

      if (task.id == null) {
        task.id = this.getId();
        lane.tasks.push(task);
      } else {
        let index = lane.tasks.findIndex((item) => item.id == task.id);
        lane.tasks[index] = task;
      }
    });
//suscribirse a emitDeleteLane y eliminar con splice
    this.KanbasService.emitDeleteLane.subscribe((i)=>{
      console.log('aqui elminar lane', i);
      this.lanes.splice(i,1);
    });
    
    this.KanbasService.emitAddKanba.subscribe((lane: Lane) => {
      

      if (lane.id == null) {
        lane.id = this.lanes.length + 1;
        this.lanes.push(lane);
      } else {
        let index = this.lanes.findIndex((item) => item.id == lane.id);
        this.lanes[index].title = lane.title;
      }
      this.lanes.forEach((e, i) => {
        this.kanbasListId.push('list' + i);
      });
    });
  }

  drop(event: any) {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
    console.log('event.previousContainer.data', event.previousContainer.data);
    console.log('event.container.data', event.container.data);
    console.log('event.previousIndex', event.previousIndex);
    console.log('event.currentIndex', event.currentIndex);

    console.log('lista', this.lanes);
  }

  getId() {
    let c = 1;
    this.lanes.forEach((r) => {
      r.tasks.forEach((l) => {
        c++;
      });
    });
    return c++;
  }
  
  
  add() {
    const dialogRef = this.dialog.open(LaneEditComponent, {
      data: {},
    });
  }



}
