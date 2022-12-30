import { NoteBlock } from './../../model/note-block';
import { StompService } from './../../websockect/stomp.service';
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
  @Output()emitAddNote: EventEmitter<Notes>= new EventEmitter();
  @Output()emitEditXNote: EventEmitter<Notes>= new EventEmitter();
  @Output()emitSaveLane:EventEmitter<Lane>= new EventEmitter();
  @Output()emitRemoveNote:EventEmitter<Notes>= new EventEmitter();
  @Output()emitBlockNote:EventEmitter<NoteBlock>= new EventEmitter();
  

  
  constructor(
    public kanbasService: KanbasService,
    public matDialog: MatDialog,
    
  ) {}

  ngOnInit(): void {
    console.log(this.lane)

  }
  
  dropNote(event: any, laneId: number) {
    
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

  addNote() {
    const dialogRef = this.matDialog.open(NoteEditComponent, {
      data: { lane: this.lane },
    });
    
    dialogRef.afterClosed().subscribe((result) => {
      if(result!=null)
      this.emitAddNote.emit(result);
    });
    
    return true;
  }

  editLane() {
    
    const dialogRef = this.matDialog.open(LaneEditComponent, {
      data: { 
        entitie: this.lane ,
        kanbanId:this.kanbanId
      },
    });

     dialogRef.afterClosed().subscribe(res=>{
        if(res!=null)
        this.emitSaveLane.emit(res);

     })

    return true;
  }

  deleteLane() {
    
    const dialogRef = this.matDialog.open(DialogConfirmationComponent, {

      //Language control required, new component needed
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
    
     this.emitEditXNote.emit(note);
  }
  
  deleteNote(note:Notes){
    
    this.emitRemoveNote.emit(note);
  }

  blockNote(noteBlock:NoteBlock){
    noteBlock.swimlaneId = this.lane.id;
    this.emitBlockNote.emit(noteBlock);
  }
}
