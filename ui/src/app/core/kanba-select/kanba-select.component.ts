import { Kanba } from '../../kanbas/model/Kanbas';
import { KanbasService } from '../../kanbas/kanbas.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-kanba-select',
  templateUrl: './kanba-select.component.html',
  styleUrls: ['./kanba-select.component.css']
})
export class KanbaSelectComponent implements OnInit {
   
  listKanbas:Kanba[] = [
    
  ] ;

  select:Kanba = null;
  constructor( 
     private KanbasService: KanbasService
    ) { }

  ngOnInit(): void {
    this.KanbasService.getKanbas().subscribe(x=>{
      this.listKanbas=x;
    })
    this.select = this.listKanbas.find(x=>x.select==true);
    this.KanbasService.emitKankaSelect.emit(this.select);
  }

  selectKanba(item:Kanba){
   
    this.select = item;     
    this.KanbasService.emitKankaSelect.emit(item);
    this.listKanbas.forEach(x=>{
      
      if(x.id!=item.id)x.select=false;
      else 
        item.select=true;
    })
    
  }

}
