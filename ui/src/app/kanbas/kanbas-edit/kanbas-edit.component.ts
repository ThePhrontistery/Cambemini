import { Component, OnInit, Inject} from '@angular/core';
import { KanbasService } from '../kanbas.service';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Kanban } from '../model/Kanbas';

@Component({
  selector: 'app-kanbas-edit',
  templateUrl: './kanbas-edit.component.html',
  styleUrls: ['./kanbas-edit.component.css']
})
export class KanbasEditComponent implements OnInit {

  kanba:Kanban;
  busy:boolean = false;  
  constructor(private kanbasService:KanbasService,
              public dialogRef:MatDialogRef<KanbasEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    if(this.data == null){
      this.kanba = new Kanban();

      this.kanba.swimlanes=[];
    }      
    else{
      this.kanba = Object.assign({}, this.data);        
    }
  }

  onCancel(){
    this.dialogRef.close();
    return true;
  }

  onSave(){

    if(this.busy === true) return false ;
    this.busy = true;
    this.kanba.code = this.kanba.title;
    
    this.kanbasService.saveKanban(this.kanba).subscribe(result =>  {
      this.dialogRef.close();
    }); 
         
    return true;
    
  }

}
