import { Kanban } from './../../kanbas/model/Kanban';
import { NavigationEnd, Router } from '@angular/router';
import { UserJwt } from './../../kanbas/model/UserJwt';
import { Observable, map } from 'rxjs';
import { LoginService } from './../../login/login.service';

import { KanbasService } from './../../kanbas/kanbas.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/kanbas/model/User';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isLoggedIn: Observable<boolean>;
  users:User[]=[];
  user:UserJwt;  
  kanban:Kanban;
  constructor(
      public kanbasService:KanbasService,
      public loginService:LoginService ,
      public router:Router,

    ) {
    this.user= new UserJwt();
   }

  ngOnInit(): void {

    this.kanbasService.emitKankaSelect.subscribe(kanban=>{
      
      this.kanban = kanban!=null ? kanban :null;

      if(kanban!=null) this.users = kanban.userKanbanPermission.map(kan=>kan.users);
       
    })
    this.isLoggedIn = this.loginService.isLoggedIn;

     this.loginService.userJwt.subscribe(user=>{
      this.user =user;
    })

    this.router.events.subscribe(x=>{
        
        if(x instanceof NavigationEnd){
           if(x.url=="/kanbans"){
             this.kanban = null; 
           }
        }
    });
  }

  onLogout() {
    this.loginService.logout();
  }

}
