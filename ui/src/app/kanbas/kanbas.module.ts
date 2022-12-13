import { NoteEditComponent } from './kanban/lane/note-edit/note-edit.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { KanbanComponent } from './kanban/kanban.component';
import { NoteComponent } from './kanban/lane/note/note.component';
import { LaneComponent } from './kanban/lane/lane.component';
import {  NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatDividerModule} from '@angular/material/divider';
import {MatCardModule} from '@angular/material/card';
import {DragDropModule} from '@angular/cdk/drag-drop';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { LaneEditComponent } from './kanban/lane-edit/lane-edit.component';
import { KanbasListComponent } from './kanbas-list/kanbas-list.component';
import { KanbanEditComponent } from './kanbas-list/kanban-edit/kanban-edit.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { AttachmentViewerComponent } from './kanban/lane/note/attachment-viewer/attachment-viewer.component';

import { PdfViewerModule } from 'ng2-pdf-viewer';
import { BrowserModule } from '@angular/platform-browser';
@NgModule({
  declarations: [
    LaneComponent,
    NoteComponent,
    KanbanComponent,
    KanbanEditComponent,
    NoteEditComponent,
    LaneEditComponent,
    KanbasListComponent,
    AttachmentViewerComponent, 
  ],
  imports: [
    CommonModule,
    MatDividerModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    DragDropModule,   
    MatIconModule,     
    MatDialogModule,
    MatFormFieldModule,
    MatProgressBarModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    PdfViewerModule,
    BrowserModule,
  ],
  
})
export class KanbasModule { }
