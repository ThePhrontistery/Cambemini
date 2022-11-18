import { Lane } from '../model/Kanbas';
import { KanbasService } from '../kanbas.service';
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
    private kanbanService: KanbasService
  ) {}

  ngOnInit(): void {
    if (this.data.lane != null) {
      this.lane = Object.assign({}, this.data.lane);
    } else {
      this.lane = new Lane();
      this.lane.tasks = [];
    }
  }

  onSave() {
    this.kanbanService.save(this.lane);
    this.dialogRef.close();
  }

  onClose() {
    this.dialogRef.close();
  }
}
