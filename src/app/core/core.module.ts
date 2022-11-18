import { UserSelectComponent } from './user-select/user-select.component';
import { KanbaSelectComponent } from './kanba-select/kanba-select.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { DialogConfirmationComponent } from './dialog-confirmation/dialog-confirmation.component';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
@NgModule({
  declarations: [HeaderComponent, DialogConfirmationComponent,KanbaSelectComponent, UserSelectComponent],
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule, 
    MatMenuModule,
    MatToolbarModule,
    MatDialogModule,
    MatButtonModule,
  ],
  providers: [
    {
      provide: MAT_DIALOG_DATA,
      useValue: {},
    }
  ],
  exports: [
    HeaderComponent
  ]
})
export class CoreModule { }
