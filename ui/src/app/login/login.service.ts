import { UserJwt } from './../kanbas/model/UserJwt';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, map, Observable, take } from 'rxjs';
import { User } from '../kanbas/model/User';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Permission } from '../kanbas/model/Permission';
import { Kanban } from '../kanbas/model/Kanban';
import { KanbasService } from '../kanbas/kanbas.service';
import { LoginRequest } from './login/model/LoginRequest';
import { LoginReply } from './login/model/LoginReply';



@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public userJwt: BehaviorSubject<UserJwt> = new BehaviorSubject<UserJwt>(null);
  public user: BehaviorSubject<User> = new BehaviorSubject<User>(null);
  public checkIsLoggedIn: boolean;
  public token: BehaviorSubject<String> = new BehaviorSubject<String>(null);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(
    private router: Router,
    private httpClient: HttpClient,
    private kanbanService: KanbasService,
  ) {
    this.loggedIn.subscribe(result => {
      this.checkIsLoggedIn = result;
    })
  }

  login(user: User, sharedKanban?: Kanban) {
    // this.loggedIn.next(true);
    
    let loginRequest: LoginRequest = {
      email: user.email,
      password: user.password,
    };

    this.secureLogin(loginRequest).subscribe(loginReply => {
      this.user.next(loginReply.user);
      this.token.next(loginReply.token);

      if(sharedKanban != null)
      this.kanbanService.saveUserKanbanPermission(loginReply.user, sharedKanban).subscribe(result =>{});

      this.loggedIn.next(true);
      this.router.navigate(['/']);
    });
  }

  getUserByEmail(email: string): Observable<User> {
    let url = environment.url + 'users?email=' + email;
    return this.httpClient.get<User>(url);
  }

  loginGoogle(credencial:any){
    try{
      
      let objectUser = this.decodificarJwt(credencial);
    
    let user = new UserJwt();
    user.email =objectUser.email; 
    user.email_verified = objectUser.email_verified; 
    user.family_name = objectUser.family_name; 
    user.given_name = objectUser.give_name; 
    user.name = objectUser.name; 
    user.iss = objectUser.iss; 
    user.picture = objectUser.picture; 

    this.userJwt.next(user);
    this.loggedIn.next(true);
    this.getUserByEmail(user.email).subscribe(result => {
      this.user.next(result);
    });
    
      return true;
    }catch(e){
      this.userJwt.next(null);
      this.loggedIn.next(false);    
      return false;
    }
  
  }

  private decodificarJwt(token:string):any
  {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
  }

  secureLogin(req: LoginRequest): Observable<LoginReply> {
    let url = environment.auth + 'login';
    return this.httpClient.post<LoginReply>(url, req);
  }

  logout() {    
    sessionStorage.removeItem('token')
    this.loggedIn.next(false);
    this.userJwt.next(null);
    this.router.navigate(['/login']);
  }
  
}
