
import { UserJwt } from './../../kanbas/model/UserJwt';
import { Observable } from 'rxjs';
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

    this.kanbasService.emitKankaSelect.subscribe(x=>{
      this.users=x.users
       
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
