import { DialogConfirmationComponent } from '../../core/dialog-confirmation/dialog-confirmation.component';
import { KanbanEditComponent } from '../kanban-edit/kanban-edit.component';
import { MatDialog } from '@angular/material/dialog';
import { KanbasService } from '../kanbas.service';
import { Router } from '@angular/router';
import { Kanban } from '../model/Kanban';
import { Component, OnInit } from '@angular/core';
import { UserKanbanPermission } from '../model/User-Kanban-Permission';
import { User } from '../model/User';

@Component({
  selector: 'app-kanbas',
  templateUrl: './kanbas-list.component.html',
  styleUrls: ['./kanbas-list.component.css'],
})
export class KanbasListComponent implements OnInit {
  listKanbas: Kanban[] = [];
  usersKanban: User[][] = [];

  userId = 1;

  constructor(
    private router: Router,
    private kanbaService: KanbasService,
    public matDialog: MatDialog,
    

  ) {}

  ngOnInit(): void {
    this.kanbaService.getKanbansFromUser(1).subscribe((kanba) => {
      this.listKanbas = kanba;
    });

    for(let i = 0; i < this.listKanbas.length; i++){
      this.kanbaService.getUsersFromKanban(this.listKanbas[i].id).subscribe(result => {
        this.usersKanban[i] = result;
      });
    }
   
  }
 
  go(item: Kanban) {
    this.kanbaService.kanba = item;
    this.router.navigate(['kanbas', item.id, 1]);
    return true;
  }

  remove(kanban: Kanban) {
    const dialogRef = this.matDialog.open(DialogConfirmationComponent, {
      data: {
        title: 'Eliminar kanban',
        description:
          'Atención si borra el kanban:' +
          kanban.title +
          ' se perderán sus datos.<br> ¿Desea eliminar el kanban?',
      },
    });
     
    dialogRef.afterClosed().subscribe((result) => {
       if (result) {
         this.kanbaService.removeKanban(kanban).subscribe((result) => {
           this.ngOnInit();
         });
       }
     });
  }

  newKanba() {
    const dialogRef = this.matDialog.open(KanbanEditComponent, {
      data: {
        userId: this.userId,
        kanban: null
      }
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ngOnInit();
    });

    return true;
  }

  edit(kanba: Kanban) {
    const dialogRef = this.matDialog.open(KanbanEditComponent, {
      data: {
        userId: this.userId,
        kanban: kanba
      }
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ngOnInit();
    });

    return true;
  }
}
