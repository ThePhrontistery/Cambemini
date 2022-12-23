import { Kanban } from './../../model/Kanban';
import { UserKanbanPermission } from './../../model/User-Kanban-Permission';
import { KanbasService } from 'src/app/kanbas/kanbas.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';
import { Permission } from '../../model/Permission';
import { MatSelectChange } from '@angular/material/select';

@Component({
  selector: 'app-permision-edit',
  templateUrl: './permision-edit.component.html',
  styleUrls: ['./permision-edit.component.css']
})
export class PermisionEditComponent implements OnInit {
  userKanbanPermission:UserKanbanPermission;
  title:string;
  KanbanId:number;
  permisionList:Permission[]=[{id:1,rol:'Owner'},{id:2,rol:'Editor'},{id:3,rol:'Collaborator'}]
  constructor(
    public dialogRef: MatDialogRef<PermisionEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private KanbasService: KanbasService
    ){

    }

  ngOnInit(): void {
    
    if (this.data.userKanbanPermission != null) {
      this.userKanbanPermission = Object.assign({}, this.data.userKanbanPermission);
      this.title = this.data.title;
      this.KanbanId = this.data.kanbanId;
    } 

  }
  onSave() {
     this.KanbasService.saveUserKanbanPermissionRol(this.KanbanId,this.userKanbanPermission.users.id,this.userKanbanPermission.permission.id).subscribe(result=>{      
       this.dialogRef.close(this.userKanbanPermission);
     });
    
    return true;
  }

  change(event:MatSelectChange){
    this.userKanbanPermission.permission.rol = this.permisionList.find(per=>per.id==event.value).rol;
  }

  onClose() {
    this.dialogRef.close();
    return true;
  }

}
