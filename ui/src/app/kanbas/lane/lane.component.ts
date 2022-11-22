import { KanbasService } from './../kanbas.service';
import { Lane } from './../model/Lane';
import { LaneEditComponent } from '../lane-edit/lane-edit.component';
import { TaskEditComponent } from '../task-edit/task-edit.component';
import { MatDialog } from '@angular/material/dialog';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';

import { Component, Input, OnInit } from '@angular/core';

// const eventData=[object Object]
@Component({
  selector: 'app-lane',
  templateUrl: './lane.component.html',
  styleUrls: ['./lane.component.css'],
})
export class LaneComponent implements OnInit {
  @Input() lane: Lane;
  @Input() index: number;
  @Input() listId: string[];
  constructor(
    public kanbasService: KanbasService,
    public matDialog: MatDialog
  ) {}

  ngOnInit(): void {
    console.log(this.lane)
  }

  drop(event: any, laneId: number) {
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

    event.container.data.forEach((element) => {
      element.laneId = laneId;
    });
    
    console.log('event.previousContainer.data', event.previousContainer.data);
    console.log('event.container.data', event.container.data);
    console.log('event.previousIndex', event.previousIndex);
    console.log('event.currentIndex', event.currentIndex);

    console.log('event.currentIndex', console.log(laneId));
    return event;
  }

  add() {
    const dialogRef = this.matDialog.open(TaskEditComponent, {
      data: { lane: this.lane },
    });
    return true;
  }

  edit() {
    const dialogRef = this.matDialog.open(LaneEditComponent, {
      data: { entitie: this.lane },
    });
    return true;
  }

  delete() {
    return this.kanbasService.removeLane(this.lane);
  }
}
