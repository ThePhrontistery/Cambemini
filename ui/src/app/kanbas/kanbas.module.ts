import { TaskEditComponent } from './task-edit/task-edit.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { KanbaListComponent } from './kanba-list/kanba-list.component';
import { TaskComponent } from './task/task.component';
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
import { KanbasComponent } from './kanbas/kanbas.component';
import { KanbasEditComponent } from './kanbas-edit/kanbas-edit.component';
@NgModule({
  declarations: [
    LaneComponent,
    TaskComponent,
    KanbaListComponent,
    KanbasEditComponent,
    TaskComponent,
    TaskEditComponent,
    LaneEditComponent,
    KanbasComponent,    
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
