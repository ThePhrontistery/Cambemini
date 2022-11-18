import { Task } from '../model/Kanbas';
import { KanbasService } from '../kanbas.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css'],
})
export class TaskEditComponent implements OnInit {
  task: Task;

  constructor(
    public dialogRef: MatDialogRef<TaskEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private KanbasService: KanbasService
  ) {}

  ngOnInit(): void {
    if (this.data.task != null) {
      this.task = Object.assign({}, this.data.task);
    } else {
      this.task = new Task();
      this.task.LaneId = this.data.lane.id;
    }
  }

  onSave() {
    this.KanbasService.saveTask(this.task);
    this.dialogRef.close();
  }

  onClose() {
    this.dialogRef.close();
  }
}
