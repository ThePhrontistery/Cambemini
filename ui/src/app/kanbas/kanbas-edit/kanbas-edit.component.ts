import { Component, OnInit, Inject} from '@angular/core';
import { KanbasService } from '../kanbas.service';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Kanba } from '../model/Kanbas';

@Component({
  selector: 'app-kanbas-edit',
  templateUrl: './kanbas-edit.component.html',
  styleUrls: ['./kanbas-edit.component.css']
})
export class KanbasEditComponent implements OnInit {

  kanba:Kanba;
  busy:boolean = false;  
  constructor(private kanbasService:KanbasService,
              public dialogRef:MatDialogRef<KanbasEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    
    if(this.data == null){
      this.kanba = new Kanba();

      this.kanba.lanes=[];
    }      
    else{
      this.kanba = Object.assign({}, this.data);        
    }
  }

  onCancel(){
    this.dialogRef.close();
  }

  onSave(){

    if(this.busy === true) return ;
    this.busy = true;
    this.kanba.code = this.kanba.title;
    setTimeout(()=>{
      this.kanbasService.emitSaveKanba.emit(this.kanba);
      this.busy = false;
    },300);
    
    this.dialogRef.close();
  }

}
