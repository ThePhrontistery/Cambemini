import { Injectable } from '@angular/core';
import { User } from './login/model/User';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(
    private router: Router
  ) {}

  login(user: User) {
    
     if (user.email === 'ejemplo@email.com' && user.password === 'hola' ) {
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
