import { NoteEditComponent } from '../note-edit/note-edit.component';
import { KanbasService } from '../../../kanbas.service';

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Lane } from '../../../model/Lane';
import { Notes } from '../../../model/Notes';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

  @Input()item: Notes; 
  @Input()index: number;
  @Input()indexY: number;
  @Input()taskLane: Lane;
  @Output()editNote:EventEmitter<Notes> = new EventEmitter(); 
  @Output()removeNote:EventEmitter<Notes> = new EventEmitter(); 
 
  constructor(private kanbasService:KanbasService,   public dialog: MatDialog,  ) { }

  ngOnInit(): void {
  }

  remove(){
   
    this.kanbasService.removeNote(this.item).subscribe(result=>{
        this.removeNote.emit(this.item);
    })
    return true;
  }

  edit(){
    const dialogRef = this.dialog.open(NoteEditComponent, {
      data: { task: this.item ,
              lane:this.taskLane
        }
    });

    dialogRef.afterClosed().subscribe(result => {
        this.editNote.emit(this.item);
    }); 
    
    return true;
  }

}
