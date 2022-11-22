import { KanbasEditComponent } from './../kanbas-edit/kanbas-edit.component';
import { MatDialog } from '@angular/material/dialog';
import { KanbasService } from './../kanbas.service';
import { Router } from '@angular/router';
import { Kanba } from './../model/Kanbas';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-kanbas',
  templateUrl: './kanbas.component.html',
  styleUrls: ['./kanbas.component.css'],
})
export class KanbasComponent implements OnInit {
  listKanbas: Kanba[] = [];

  constructor(
    private router: Router, 
    private kanbaService: KanbasService,
    public matDialog: MatDialog,
    ) {}

  ngOnInit(): void {
    
    this.kanbaService.getKanbas().subscribe(kanba=>{
      this.listKanbas = kanba      
    });

    this.kanbaService.emitSaveKanba.subscribe(kanba=>{
        if(kanba.id== null){
          this.listKanbas.push(kanba);
        }else{

          let index = this.listKanbas.findIndex(listItenKanba=>listItenKanba.id == kanba.id);
          this.listKanbas[index]=kanba;

        }      
    });
    
  }

  go(item: Kanba) {
    this.kanbaService.kanba = item;
    this.router.navigate(['kanbas',item.code]);
  }

  remove(index: number) {
    this.listKanbas.splice(index, 1);
  }

  newKanba(){
    const dialogRef = this.matDialog.open(KanbasEditComponent, {
      data: null,
    });    
    
    return true;
  }

  edit(kanba:Kanba){
    const dialogRef = this.matDialog.open(KanbasEditComponent, {
      data: kanba,
    });
  }
}
