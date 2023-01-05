import { Kanban } from '../../model/Kanban';
import { Lane } from '../../model/Lane';

import { KanbasService } from '../../kanbas.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-lane-edit',
  templateUrl: './lane-edit.component.html',
  styleUrls: ['./lane-edit.component.css'],
})
export class LaneEditComponent implements OnInit {
  lane: Lane;

  constructor(
    public dialogRef: MatDialogRef<LaneEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private KanbasService: KanbasService
  ) {}

  ngOnInit(): void {
   
    if (this.data.entitie != null) {
      this.lane = Object.assign({}, this.data.entitie);
    } else {
      this.lane = new Lane();     
      this.lane.notes = [];
      this.lane.order = this.data.order;
    }

    this.lane.kanban = new Kanban()
    this.lane.kanban.id = this.data.kanbanId;
  }

  onSave() {
    
    this.KanbasService.saveSwimlane(this.lane, this.lane.kanban.id).subscribe(result=>{      
      this.dialogRef.close(this.lane);
    });
    
    return true;
  }

  onClose() {
    this.dialogRef.close();
    return true;
  }
}
