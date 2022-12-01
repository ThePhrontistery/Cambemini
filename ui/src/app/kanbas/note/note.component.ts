import { NoteEditComponent } from '../note-edit/note-edit.component';
import { KanbasService } from '../kanbas.service';

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Lane } from '../model/Lane';
import { Notes } from '../model/Notes';

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

 
  constructor(private kanbasService:KanbasService,   public dialog: MatDialog,  ) { }

  ngOnInit(): void {
  }

  remove(){
    this.kanbasService.emitDeleteCard.emit({KanbaIndex:this.indexY,Itemindex:this.index});

    return true;
  }

  edit(){
    const dialogRef = this.dialog.open(NoteEditComponent, {
      data: { task: this.item ,
              lane:this.taskLane
        }
    });

    dialogRef.afterClosed().subscribe(result => {
        this.ngOnInit();
    }); 
    
    return true;
  }

}
