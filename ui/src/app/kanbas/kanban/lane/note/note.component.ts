import { NoteBlock } from './../../../model/note-block';
import { StompService } from './../../../websockect/stomp.service';
import { DialogConfirmationComponent } from './../../../../core/dialog-confirmation/dialog-confirmation.component';
import { Attachment } from './../../../model/attachment';
import { NoteEditComponent } from '../note-edit/note-edit.component';
import { KanbasService } from '../../../kanbas.service';

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Lane } from '../../../model/Lane';
import { Notes } from '../../../model/Notes';
import { finalize, Subscription } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { AttachmentViewerComponent } from './attachment-viewer/attachment-viewer.component';
@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css'],
})
export class NoteComponent implements OnInit {
  @Input() note: Notes;
  @Input() index: number;
  @Input() indexY: number;
  @Input() swimlane: Lane;
  @Input() isEditorOwner: boolean;
  @Output() emitEditNote: EventEmitter<Notes> = new EventEmitter();
  @Output() emitRemoveNote: EventEmitter<any> = new EventEmitter();
  @Output() emitBlockNote: EventEmitter<NoteBlock> = new EventEmitter();

  //Variables usadas en la subida de un attachment
  uploadProgress:number;
  uploadSub: Subscription;

  //Style of the note when it is blocked by another user
  styleNoteBlocked = ''

  noteIsBlocked :boolean = false;
  constructor(
    private kanbasService: KanbasService,
    public dialog: MatDialog,
    private stompService:StompService

     ) {}

  ngOnInit(): void {
    if(this.note.usersBlock!=null){
      this.styleNoteBlocked = "border: 1rem solid red; display: flex; flex-direction: column;"
    }
  }

  removeNote() {
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: {
        title: 'Eliminar nota',
        description:
          'Atención si borra la nota:' +
          this.note.content +
          ' se perderán sus datos.<br> ¿Desea eliminar la nota?',
      },
    });
     
    dialogRef.afterClosed().subscribe((result) => {
       if (result) {
        this.kanbasService.removeNote(this.note).subscribe((result) => {
          this.emitRemoveNote.emit({
            note: this.note,
            indexLanba: this.indexY,
            indexNote: this.index,
          });
        });
       }
     });
    
    return true;
  }

  editNote() {
    const dialogRef = this.dialog.open(NoteEditComponent, {
      data: { task: this.note, lane: this.swimlane },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result != null) this.emitEditNote.emit(result);
    });

    return true;
  }

   onFileSelected(event) {

    const file : File = event.target.files[0];

    if(file){
      
      let attachment= new Attachment();
      attachment.name = file.name;

      const upload =  this.kanbasService.uploadAttachment(this.note.id, file).pipe(
        finalize(() =>{
          this.resetUploadProgress()
        })
      );

      this.uploadSub = upload.subscribe(event => {
        if(event instanceof HttpResponse){  
          if(this.note.attachment==null){
            this.note.attachment = [];
          }        
           this.note.attachment.push(event.body);
        }
         
        if (event.type == HttpEventType.UploadProgress) {
          this.uploadProgress = Math.round(100 * (event.loaded / event.total));
        }
      });
    }

  }

  cancelUpload() {
    this.uploadSub.unsubscribe();
    this.resetUploadProgress();
  }

  //resetea las variables de progreso para que desaparezca la barra del % de subida
  resetUploadProgress() {
    this.uploadProgress = null;
    this.uploadSub = null;
  }

  downloadFile(att:Attachment) {
    this.kanbasService.downloadAttachment(att.document_path).subscribe(response => {
      debugger
      const fileName = response.headers.get('content-disposition')?.split(';')[1].split('=')[1];
      const file = response.body as Blob;
      const blobUrl = URL.createObjectURL(file);
      const a = document.createElement('a');
      a.style.display = 'none';
      a.href = blobUrl;
      a.download = fileName;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      URL.revokeObjectURL(blobUrl);
    });
  }

  viewFile(att:Attachment){
    const dialogRef = this.dialog.open(AttachmentViewerComponent, {
      data: { attachment: att },
    });
  }
  
  attachmentDelete(att:Attachment){
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: {
        title: 'Eliminar fichero',
        description:
          'Atención si borra el fichero:' +
          att.name +
          ' se perderán sus datos.<br> ¿Desea eliminar el fichero?',
      },
    });
     
    dialogRef.afterClosed().subscribe((result) => {
       if (result) {
        this.kanbasService.removeAttachment(att.id).subscribe(r=>{
           let index=this.note.attachment.findIndex(tatt=> tatt.id==att.id);
           this.note.attachment.splice(index,1);
        })
       }
     });
  }

  blockNote(blockNote:boolean){
    
     let noteBlock:NoteBlock = {noteId:this.note.id,swimlaneId:null,userId:null, block:blockNote};
     this.emitBlockNote.emit(noteBlock);
     this.noteIsBlocked = blockNote;
  }

  blocker(){
    this.noteIsBlocked = !this.noteIsBlocked;
    this.blockNote(this.noteIsBlocked);
  }
  
}
