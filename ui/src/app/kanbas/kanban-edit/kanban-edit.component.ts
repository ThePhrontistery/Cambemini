import { Component, OnInit, Inject} from '@angular/core';
import { KanbasService } from '../kanbas.service';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Kanban } from '../model/Kanban';

@Component({
  selector: 'app-kanbas-edit',
  templateUrl: './kanban-edit.component.html',
  styleUrls: ['./kanban-edit.component.css']
})
export class KanbanEditComponent implements OnInit {

  kanba:Kanban;
  userId: number;
  busy:boolean = false;  
  constructor(private kanbasService:KanbasService,
              public dialogRef:MatDialogRef<KanbanEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    if(this.data.kanban == null){
      this.kanba = new Kanban();
    }      
    else{
      this.kanba = Object.assign({}, this.data);        
    }

    this.userId = this.data.userId;
  }

  onCancel(){
    this.dialogRef.close();
    return true;
  }

  onSave(){

    if(this.busy === true) return false ;
    this.busy = true;
    
    
    this.kanbasService.saveKanban(this.kanba, this.userId).subscribe(result =>  {
      this.dialogRef.close();
    }); 
         
    return true;
    
  }

}
