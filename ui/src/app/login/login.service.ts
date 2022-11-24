import { Injectable } from '@angular/core';
import { User } from './login/model/User';
import { Router } from '@angular/router';
import { BehaviorSubject, map, take } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
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

  logout() {
    
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }
  
}
