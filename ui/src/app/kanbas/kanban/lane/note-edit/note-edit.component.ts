import { Lane } from '../../../model/Lane';

import { KanbasService } from '../../../kanbas.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';
import { Notes } from '../../../model/Notes';


@Component({
  selector: 'app-note-edit',
  templateUrl: './note-edit.component.html',
  styleUrls: ['./note-edit.component.css'],
})
export class NoteEditComponent implements OnInit {
  note: Notes;

  constructor(
    public dialogRef: MatDialogRef<NoteEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private KanbasService: KanbasService
  ) {}

  ngOnInit(): void {
    
    if (this.data.task != null) {
      this.note = Object.assign({}, this.data.task);
    } else {
      this.note = new Notes();      
    }
    this.note.swimlane= new Lane();
    this.note.swimlane.id= this.data.lane.id;
   
  }

  async onSave() {
    this.KanbasService.saveNote(this.note).subscribe(note=>{   
        this.note.id = note.id
        this.dialogRef.close(this.note);
    })
    
  }

  onClose() {
    this.dialogRef.close();
  }
}
