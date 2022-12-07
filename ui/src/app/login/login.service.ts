import { UserJwt } from './../kanbas/model/UserJwt';
import { Injectable } from '@angular/core';
import { User } from './login/model/User';
import { Router } from '@angular/router';
import { BehaviorSubject, map, take } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public user: BehaviorSubject<UserJwt> = new BehaviorSubject<UserJwt>(null);
  public checkIsLoggedIn: boolean;

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  

  constructor(
    private router: Router
  ) {
    this.loggedIn.subscribe(result => {
      this.checkIsLoggedIn = result;
    })
  }

  login(user: User) {
    // this.loggedIn.next(true);
    
     if (user.email === 'ejemplo@capgemini.com' && user.password === 'hola' ) {
       this.loggedIn.next(true);
       this.router.navigate(['/']);
     }else{
       alert("Usuario o contraseña incorrectos!")
     }
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

    this.user.next(user);
    this.loggedIn.next(true);
    
      return true;
    }catch(e){
      this.user.next(null);
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

  logout() {    
    sessionStorage.removeItem('token')
    this.loggedIn.next(false);
    this.user.next(null);
    this.router.navigate(['/login']);
  }
  
}
