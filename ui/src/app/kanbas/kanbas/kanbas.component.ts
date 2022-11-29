import { DialogConfirmationComponent } from './../../core/dialog-confirmation/dialog-confirmation.component';
import { KanbasEditComponent } from './../kanbas-edit/kanbas-edit.component';
import { MatDialog } from '@angular/material/dialog';
import { KanbasService } from './../kanbas.service';
import { Router } from '@angular/router';
import { Kanban } from '../model/Kanbas';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-kanbas',
  templateUrl: './kanbas.component.html',
  styleUrls: ['./kanbas.component.css'],
})
export class KanbasComponent implements OnInit {
  listKanbas: Kanban[] = [];

  constructor(
    private router: Router,
    private kanbaService: KanbasService,
    public matDialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.kanbaService.getKanban().subscribe((kanba) => {
      this.listKanbas = kanba;
    });
   
  }

  go(item: Kanban) {
    this.kanbaService.kanba = item;
    this.router.navigate(['kanbas', item.code]);
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
    this.kanbaService.removeKanban(kanban).subscribe((result) => {
      this.ngOnInit();
    });
    // dialogRef.afterClosed().subscribe((result) => {
    //   if (result) {
    //     this.kanbaService.removeKanban(kanban).subscribe((result) => {
    //       this.ngOnInit();
    //     });
    //   }
    // });
  }

  newKanba() {
    const dialogRef = this.matDialog.open(KanbasEditComponent, {
      data: null,
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ngOnInit();
    });

    return true;
  }

  edit(kanba: Kanban) {
    const dialogRef = this.matDialog.open(KanbasEditComponent, {
      data: kanba,
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ngOnInit();
    });

    return true;
  }
}
