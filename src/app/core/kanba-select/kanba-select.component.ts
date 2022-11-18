import { kanba } from '../../kanbas/model/Kanbas';
import { KanbasService } from '../../kanbas/kanbas.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-kanba-select',
  templateUrl: './kanba-select.component.html',
  styleUrls: ['./kanba-select.component.css']
})
export class KanbaSelectComponent implements OnInit {
   
  listKanbas:kanba[] = [
    { id:1,title:"Escape", select:true, icon:"settings_accesibility",users:[{email:'fredy@test.com',online:true},{email:'david@test.com',online:false}
    ]},
    { id:2,title:"Site", select:false, icon:"settings_accesibility",users:[{email:'mercedes@test.com',online:false},{email:'raul@test.com',online:true}]}
  ] ;

  select:kanba = null;
  constructor( 
     private KanbasService: KanbasService
    ) { }

  ngOnInit(): void {
    this.select = this.listKanbas.find(x=>x.select==true);
    this.KanbasService.emitKankaSelect.emit(this.select);
  }

  selectKanba(item:kanba){
   
    this.select = item;     
    this.KanbasService.emitKankaSelect.emit(item);
    this.listKanbas.forEach(x=>{
      
      if(x.id!=item.id)x.select=false;
      else 
        item.select=true;
    })
    
  }

}
