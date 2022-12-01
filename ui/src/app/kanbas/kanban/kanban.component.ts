
import { Lane } from '../model/Lane';
import { Title } from '@angular/platform-browser';
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
import { Task } from '../model/Task';
@Component({
  selector: 'app-kanba-list',
  templateUrl: './kanban.component.html',
  styleUrls: ['./kanban.component.scss'],
})
export class KanbanComponent implements OnInit {
  lanes: Lane[] = [];
  kanbasListId: string[] = [];
  code: string;
  constructor(
    private KanbasService: KanbasService,
    private dialog: MatDialog,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.code = this.activatedRoute.snapshot.params.code;
    this.KanbasService.getKanban().subscribe((kanbas) => {
      let kanba = kanbas.find((kanba) => kanba.code == this.code);
      // this.lanes.push(...this.KanbasService.kanba.lanes);
      this.lanes.push(...kanba.swimlanes);
    });

    this.lanes.forEach((e, i) => {
      this.kanbasListId.push('list' + i);
    });

    this.KanbasService.emitDeleteCard.subscribe((y) => {
      this.lanes[y.KanbaIndex].items.splice(y.Itemindex, 1);
    });

    this.KanbasService.emitAddCard.subscribe((entitie: Task) => {
      let kanba = this.lanes.find((x) => x.id == entitie.laneId);

      if (entitie.id == null) {
        entitie.id = this.getId();
        kanba.items.push(entitie);
      } else {
        let index = kanba.items.findIndex((item) => item.id == entitie.id);
        kanba.items[index] = entitie;
      }
    });

    this.KanbasService.emitAddKanba.subscribe((entitie: Lane) => {
      if (entitie.id == null) {
        entitie.id = this.lanes.length + 1;
        this.lanes.push(entitie);
      } else {
        let index = this.lanes.findIndex((item) => item.id == entitie.id);
        this.lanes[index].title = entitie.title;
      }
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
      r.items.forEach((l) => {
        c++;
      });
    });
    return c++;
  }

  add() {
    const dialogRef = this.dialog.open(LaneEditComponent, {
      data: {},
    });

    return true;
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.lanes, event.previousIndex, event.currentIndex);
  }
    
}
