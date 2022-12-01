import { NoteEditComponent } from './note-edit/note-edit.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { KanbanComponent } from './kanban/kanban.component';
import { NoteComponent } from './note/note.component';
import { LaneComponent } from './lane/lane.component';
import {  NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatDividerModule} from '@angular/material/divider';
import {MatCardModule} from '@angular/material/card';
import {DragDropModule} from '@angular/cdk/drag-drop';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { LaneEditComponent } from './lane-edit/lane-edit.component';
import { KanbasListComponent } from './kanbas-list/kanbas-list.component';
import { KanbanEditComponent } from './kanban-edit/kanban-edit.component';
@NgModule({
  declarations: [
    LaneComponent,
    NoteComponent,
    KanbanComponent,
    KanbanEditComponent,
    NoteEditComponent,
    LaneEditComponent,
    KanbasListComponent,    
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
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    
  ],
  
})
export class KanbasModule { }
