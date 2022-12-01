import { CheckComponent } from './login/check/check.component';
import { KanbasListComponent } from './kanbas/kanbas-list/kanbas-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { KanbanComponent } from './kanbas/kanban/kanban.component';
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
    path: 'kanbas/:id',
    component: KanbanComponent,
    canActivate: [LoginGuard],
    children: [
      {
        path: 'kanbas/:id',
        component: KanbanComponent,
      },
    ],
  },
  {
    path: 'kanbas',
    component: KanbasListComponent,
    canActivate: [LoginGuard],
    children: [
      {
        path: 'kanbas',
        component: KanbanComponent,
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
  {
    path: 'check',
    component: CheckComponent,
    children: [
      {
        path: 'check',
        component: CheckComponent,
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
