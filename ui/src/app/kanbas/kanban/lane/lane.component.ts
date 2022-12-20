import { Notes } from '../../model/Notes';
import { DialogConfirmationComponent } from '../../../core/dialog-confirmation/dialog-confirmation.component';
import { KanbasService } from '../../kanbas.service';
import { Lane } from '../../model/Lane';
import { LaneEditComponent } from '../lane-edit/lane-edit.component';
import { NoteEditComponent } from './note-edit/note-edit.component';
import { MatDialog } from '@angular/material/dialog';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';

import { Component, Input, OnInit, Output, EventEmitter, DebugElement } from '@angular/core';
import { outputAst } from '@angular/compiler';

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
  @Input() isEditorOwner: boolean;
  @Output()addNote: EventEmitter<Notes>= new EventEmitter();
  @Output()editXNote: EventEmitter<Notes>= new EventEmitter();
  @Output()saveLane:EventEmitter<Lane>= new EventEmitter();
  @Output()removeNote:EventEmitter<Notes>= new EventEmitter();


  
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
        
    event.container.data.forEach((note, index) => {
      
      note.swimlane = new Lane();
      note.swimlane.id = laneId;
      if(note.order != index)
        note.order = index;

      //Esto realiza muchas peticiones, no debería de ser así, pero de momento funciona
      this.kanbasService.saveNote(note).subscribe(x=>{
      });
    });
    return event;
  }

  add() {
    const dialogRef = this.matDialog.open(NoteEditComponent, {
      data: { lane: this.lane },
    });
    
    dialogRef.afterClosed().subscribe((result) => {
      this.addNote.emit(result);
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
   
        this.saveLane.emit(res);

     })

    return true;
  }

  delete() {
    
    const dialogRef = this.matDialog.open(DialogConfirmationComponent, {
      data: {
        title: 'Eliminar un carril',
        description:
          'Atención si borra el carril:' +
          this.lane.title +
          ' se perderán sus datos.<br> ¿Desea eliminar el carril?',
      },
    });
    
    dialogRef.afterClosed().subscribe((result) => {
       if (result) {
          return this.kanbasService.removeLane(this.lane).subscribe((result) => {
            this.kanbasService.emitRemoveLane.emit(this.lane);
          });
       }
     });
    
  }

  editNote(note:Notes){
    
     this.editXNote.emit(note);
  }
  
  deleteNote(note:Notes){
    
    this.removeNote.emit(note);
  }
}
