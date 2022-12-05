import { Kanban } from '../../kanbas/model/Kanban';
import { KanbasService } from '../../kanbas/kanbas.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-kanba-select',
  templateUrl: './kanba-select.component.html',
  styleUrls: ['./kanba-select.component.css']
})
export class KanbaSelectComponent implements OnInit {
   
  listKanbas:Kanban[] = [
    
  ] ;

  select:Kanban = null;
  constructor( 
     private KanbasService: KanbasService
    ) { }

  ngOnInit(): void {
    this.KanbasService.getKanbansFromUser(1).subscribe(x=>{
      this.listKanbas=x;
    })
    this.select = this.listKanbas.find(x=>x.select==true);
    this.KanbasService.emitKankaSelect.emit(this.select);
  }

  selectKanba(item:Kanban){
   
    this.select = item;     
    this.KanbasService.emitKankaSelect.emit(item);
    this.listKanbas.forEach(x=>{
      
      if(x.id!=item.id)x.select=false;
      else {
        x.select=true;
        item.select=true;
      }
        
    })
    
  }

}
