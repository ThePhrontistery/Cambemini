import { Kanban } from '../../kanbas/model/Kanban';

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
  constructor(public kanbasService:KanbasService, public loginService:LoginService) {
    this.user= new UserJwt();
   }

  ngOnInit(): void {

    this.kanbasService.emitKankaSelect.subscribe(kanban=>{
      
      if(kanban!=null){
        this.kanbasService.getUsersFromKanban(kanban.id).subscribe(result => {
          this.users = result;
        });
      }
       
    })
    this.isLoggedIn = this.loginService.isLoggedIn;

     this.loginService.user.subscribe(user=>{
      this.user =user;
    })
  }

  onLogout() {
    this.loginService.logout();
  }

}
