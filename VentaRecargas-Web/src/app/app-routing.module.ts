import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './config/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    loadChildren: ()=> import('./feature/session/session.module').then(m => m.SessionModule)
  },
  {
    path: 'recargas',
    loadChildren: ()=> import('./feature/sales-refills/sales-refills.module').then(m => m.SalesRefillsModule),
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
