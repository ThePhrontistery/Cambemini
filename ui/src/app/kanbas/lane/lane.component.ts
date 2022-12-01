import { KanbasService } from './../kanbas.service';
import { Lane } from './../model/Lane';
import { LaneEditComponent } from '../lane-edit/lane-edit.component';
import { NoteEditComponent } from '../note-edit/note-edit.component';
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
  @Input() kanbanId: number;
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

    return event;
  }

  add() {
    const dialogRef = this.matDialog.open(NoteEditComponent, {
      data: { lane: this.lane },
    });
    return true;
  }

  edit() {
    
    const dialogRef = this.matDialog.open(LaneEditComponent, {
      data: { 
        entitie: this.lane ,
        kanbanId:this.kanbanId
      },
    });

    dialogRef.afterClosed().subscribe(res=>{
      this.kanbasService.emitAddKanba.emit(res);

    })

    return true;
  }

  delete() {
    return this.kanbasService.removeLane(this.lane);
  }
}
