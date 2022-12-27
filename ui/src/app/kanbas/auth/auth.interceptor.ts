import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/login/login.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  token: String;

  constructor(private loginService: LoginService) {
    this.loginService.token.subscribe(result => {
      this.token = result;
    })
  }
  
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(this.token != null){
      request = request.clone({
        setHeaders:{Authorization: "Bearer " + this.token}
      });
    }
    return next.handle(request);
  }
}
