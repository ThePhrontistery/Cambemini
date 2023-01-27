import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Attachment } from './../../../../model/attachment';
import { KanbasService } from '../../../../kanbas.service';

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
    private kanbasService: KanbasService,
  ) { }

  ngOnInit(): void {
    this.attachment = this.data.attachment;
    let blobUrl = "";

    this.kanbasService.downloadAttachment(this.attachment.document_path).subscribe(response => {
      const fileName = response.headers.get('content-disposition')?.split(';')[1].split('=')[1];
      const file = response.body as Blob;
      blobUrl = URL.createObjectURL(file);
      this.src = blobUrl;
      this.attType = this.attachment.type;
    });
  }

  close(){
    this.dialogRef.close();
  }
}
