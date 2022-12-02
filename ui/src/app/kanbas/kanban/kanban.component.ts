
import { Lane } from '../model/Lane';

import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { KanbasService } from '../kanbas.service';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { LaneEditComponent } from '../lane-edit/lane-edit.component';
import { ActivatedRoute } from '@angular/router';
import { Notes } from '../model/Notes';
import { ThisReceiver } from '@angular/compiler';
@Component({
  selector: 'app-kanba-list',
  templateUrl: './kanban.component.html',
  styleUrls: ['./kanban.component.scss'],
})
export class KanbanComponent implements OnInit {
  lanes: Lane[] = [];
  kanbasListId: string[] = [];
  kanbanId:number;
  userId:number = 1;
  constructor(
    private KanbasService: KanbasService,
    private dialog: MatDialog,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.lanes = [];
    this.kanbasListId =[];
    this.kanbanId = Number(this.activatedRoute.snapshot.params.id);
    
    this.KanbasService.getKanban(this.userId,this.kanbanId).subscribe((kanban) => {
      this.lanes.push(...kanban[0].swimlanes);
      this.lanes.forEach((e, i) => {
        this.kanbasListId.push('list' + i);
      });
    });
    
    this.KanbasService.emitKankaSelect.subscribe((x) => {
      this.lanes = [];
      this.lanes.push(...x.swimlanes);
    });

    this.KanbasService.emitRemoveLane.subscribe((lane) => {
      let index = this.lanes.findIndex((xLane) => lane.id == xLane.id);
      if (index != -1) this.lanes.splice(index, 1);
    });
  }

  getId() {
    let c = 1;
    this.lanes.forEach((r) => {
      r.notes.forEach((l) => {
        c++;
      });
    });
    return c++;
  }

  add() {
    
    const dialogRef = this.dialog.open(LaneEditComponent, {
          data: {kanbanId:this.kanbanId},
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.ngOnInit();
      }
    });
    return true;
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.lanes, event.previousIndex, event.currentIndex);
  }
  
  saveNote(note:Notes){
    this.ngOnInit();
  }
  
  saveLane(lane:Lane){
    this.ngOnInit();
  }

  removeNote(note:Notes){
    debugger
    this.ngOnInit();
  }

 
    
}
