import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Attachment } from 'src/app/kanbas/model/attachment';

@Component({
  selector: 'app-attachment-viewer',
  templateUrl: './attachment-viewer.component.html',
  styleUrls: ['./attachment-viewer.component.css']
})
export class AttachmentViewerComponent implements OnInit {

  attachment: Attachment;
  attType: string;
  src: string = '';
  constructor(
    public dialogRef: MatDialogRef<AttachmentViewerComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit(): void {
    this.attachment = this.data.attachment;
    this.src = this.attachment.document_path;
    this.attType = this.attachment.type;
  }

  close(){
    this.dialogRef.close();
  }
}
