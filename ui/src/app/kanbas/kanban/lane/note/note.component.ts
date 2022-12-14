import { DialogConfirmationComponent } from './../../../../core/dialog-confirmation/dialog-confirmation.component';
import { Attachment } from './../../../model/attachment';
import { NoteEditComponent } from '../note-edit/note-edit.component';
import { KanbasService } from '../../../kanbas.service';

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Lane } from '../../../model/Lane';
import { Notes } from '../../../model/Notes';
import { FileType } from 'src/app/kanbas/model/file-type';

import {DomSanitizer} from '@angular/platform-browser';
import { finalize, Subscription } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { AttachmentViewerComponent } from './attachment-viewer/attachment-viewer.component';
@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css'],
})
export class NoteComponent implements OnInit {
  @Input() item: Notes;
  @Input() index: number;
  @Input() indexY: number;
  @Input() taskLane: Lane;
  @Input() isEditorOwner: boolean;
  @Output() editNote: EventEmitter<Notes> = new EventEmitter();
  @Output() removeNote: EventEmitter<any> = new EventEmitter();

  spiner:boolean = false;

  uploadProgress:number;
  uploadSub: Subscription;

  constructor(
    private kanbasService: KanbasService,
    public dialog: MatDialog,
    // private sanitizer:DomSanitizer

     ) {}

  ngOnInit(): void {}

  remove() {
    this.kanbasService.removeNote(this.item).subscribe((result) => {
      this.removeNote.emit({
        note: this.item,
        indexLanba: this.indexY,
        indexNote: this.index,
      });
    });
    return true;
  }

  edit() {
    const dialogRef = this.dialog.open(NoteEditComponent, {
      data: { task: this.item, lane: this.taskLane },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result != null) this.editNote.emit(result);
    });

    return true;
  }



  // getFileExtension(fileName: string) {
  //   return fileName
  //     .slice(fileName.lastIndexOf('.') + 1, fileName.length)
  //     .toUpperCase();
  // }

  // base64ToFile(base64Data, fileName: string) {
  //   const contentType = this.getContentType(fileName);
  //   const sliceSize = 512;

  //   const byteCharacters = atob(base64Data);
  //   let byteArrays = [];

  //   for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
  //     let slice = byteCharacters.slice(offset, offset + sliceSize);

  //     let byteNumbers = new Array(slice.length);
  //     for (let i = 0; i < slice.length; i++) {
  //       byteNumbers[i] = slice.charCodeAt(i);
  //     }

  //     const byteArray = new Uint8Array(byteNumbers);
  //     byteArrays = [...byteArrays, byteArray];
  //   }
  //   return new File(byteArrays, fileName, { type: FileType.IMAGE_PNG });
  // }

  // getFileType(fileName: string) {
  //   const fileExtension = this.getFileExtension(fileName);

  //   const options = {
  //     JPEG: 'JPEG',
  //     JPG: 'JPG',
  //     PNG: 'PNG',
  //     PDF: 'PDF',
  //   };

  //   return options[fileExtension];
  // }

  // getContentType(fileName: string) {
  //   switch (this.getFileType(fileName)) {
  //     case 'JPEG':
  //     case 'JPG':
  //       return FileType.IMAGE_JPEG;
  //     case 'PNG':
  //       return FileType.IMAGE_PNG;
  //     default:
  //       return FileType.APPLICATION_PDF;
  //   }
  // }

  // getUrl(att:Attachment){
  //   return this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.base64ToFile(att.file,att.document_path)));
  // }

   onFileSelected(event) {

    const file : File = event.target.files[0];

    if(file){
      
      let attachment= new Attachment();
      attachment.name = file.name;

      const upload =  this.kanbasService.uploadAttachment(this.item.id, file).pipe(
        finalize(() =>{
          this.reset()
        })
      );

      this.uploadSub = upload.subscribe(event => {
        if(event instanceof HttpResponse){          
           this.item.attachment.push(event.body);
        }
         
        if (event.type == HttpEventType.UploadProgress) {
          this.uploadProgress = Math.round(100 * (event.loaded / event.total));
        }
      });
    }

  }

  cancelUpload() {
    this.uploadSub.unsubscribe();
    this.reset();
  }

  reset() {
    this.uploadProgress = null;
    this.uploadSub = null;
  }

  downloadFile(att:Attachment) {
       window.open(att.document_path);
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
           let index=this.item.attachment.findIndex(tatt=> tatt.id==att.id);
           this.item.attachment.splice(index,1);
        })
       }
     });
  }
  
}
