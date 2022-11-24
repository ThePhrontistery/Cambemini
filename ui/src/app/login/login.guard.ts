import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { map, take } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {
  constructor(private loginService: LoginService, private router: Router) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.loginService.isLoggedIn.pipe(
      take(1),
      map((isLoggedIn: boolean) => {
        if (!isLoggedIn) {
          this.router.navigate(['/login']);
          return false;
        }
        return true;
      })
    );
  }

  checkLogin() {
    return this.loginService.checkIsLoggedIn;
  }
  
}
