import { Router } from '@angular/router';
import { LoginService } from './../login.service';
import { Component, OnInit } from '@angular/core';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-check',
  templateUrl: './check.component.html',
  styleUrls: ['./check.component.css']
})
export class CheckComponent implements OnInit {

  constructor(
      private loginService:LoginService,
      private router:Router
      
    ) { }

  ngOnInit(): void {
    
    let token = sessionStorage.getItem("token");

    let result = this.loginService.loginGoogle(token);

    if(result===true){
        this.router.navigate([''])
    }else{
      this.router.navigate(['login'])
    } 

  }

}
