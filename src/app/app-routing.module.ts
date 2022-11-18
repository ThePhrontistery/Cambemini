import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { KanbaListComponent } from './kanbas/kanba-list/kanba-list.component';
import { LoginComponent } from './login/login/login.component';
import { LoginGuard } from './login/login.guard';
/*
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'kanbas', component: KanbaListComponent },
  { path: 'login', component: LoginComponent}
];*/

const routes: Routes = [
  {
    path: '',
    component: KanbaListComponent,
    canActivate: [LoginGuard],
    children: [
      {
        path: '',
        component: KanbaListComponent
      }
    ]
  },
  {
    path: '',
    component: LoginComponent,
    children: [
      {
        path: 'login',
        component: LoginComponent
      }
    ]
  },
  { path: '**', redirectTo: '' }
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
