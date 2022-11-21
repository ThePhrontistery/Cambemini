
import { KanbasService } from '../kanbas.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';
import { Task } from '../model/Task';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css'],
})
export class TaskEditComponent implements OnInit {
  entitie: Task;

  constructor(
    public dialogRef: MatDialogRef<TaskEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private KanbasService: KanbasService
  ) {}

  ngOnInit(): void {
    if (this.data.entitie != null) {
      this.entitie = Object.assign({}, this.data.entitie);
    } else {
      this.entitie = new Task();
      this.entitie.id = this.data.kanba.id;
    }
  }

  onSave() {
    this.KanbasService.saveItem(this.entitie);
    this.dialogRef.close();
  }

  onClose() {
    this.dialogRef.close();
  }
}
