import { DialogConfirmationComponent } from '../../core/dialog-confirmation/dialog-confirmation.component';
import { KanbanEditComponent } from './kanban-edit/kanban-edit.component';
import { MatDialog } from '@angular/material/dialog';
import { KanbasService } from '../kanbas.service';
import { Router } from '@angular/router';
import { Kanban } from '../model/Kanban';
import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { LoginService } from 'src/app/login/login.service';
import { Permission } from '../model/Permission';

@Component({
  selector: 'app-kanbas',
  templateUrl: './kanbas-list.component.html',
  styleUrls: ['./kanbas-list.component.css'],
})
export class KanbasListComponent implements OnInit {
  listKanbas: Kanban[] = [];
  user: User;

  constructor(
    private router: Router,
    private kanbaService: KanbasService,
    public matDialog: MatDialog,
    private loginService: LoginService

  ) {}

  ngOnInit(): void {
    this.loginService.user.subscribe(user => {
      if(user != null){
        this.user = user;
        this.kanbaService.getKanbans(this.user.id).subscribe((kanba) => {
          this.listKanbas = kanba;
        });
      }
    })
  }
 
  go(item: Kanban) {
    this.kanbaService.kanba = item;
    this.router.navigate(['kanbans', item.id]);
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
      data: null,
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ngOnInit();
    });

    return true;
  }

  edit(kanba: Kanban) {
    const dialogRef = this.matDialog.open(KanbanEditComponent, {
      data: kanba,
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ngOnInit();
    });

    return true;
  }

  isOwner(indexKanban: number){
    let permission = this.listKanbas[indexKanban].userKanbanPermission
                        .find(userKP => userKP.users.id == this.user.id).permission;
    
    if(permission.rol == "Owner")
      return true;
    else
      return false;
  }

  share(kanban:Kanban){
      this.router.navigate(['kanbans/share/',kanban.id]);
  }
}
