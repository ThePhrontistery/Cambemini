import { KanbasComponent } from './kanbas/kanbas/kanbas.component';
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
  { path: '', redirectTo: '/kanbas', pathMatch: 'full' },
  {
    path: 'kanbas/:code',
    component: KanbaListComponent,
    canActivate: [LoginGuard],
    children: [
      {
        path: 'kanbas/:code',
        component: KanbaListComponent,
      },
    ],
  },
  {
    path: 'kanbas',
    component: KanbasComponent,
    canActivate: [LoginGuard],
    children: [
      {
        path: 'kanbas',
        component: KanbaListComponent,
      },
    ],
  },
  {
    path: 'login',
    component: LoginComponent,
    children: [
      {
        path: 'login',
        component: LoginComponent,
      },
    ],
  },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
