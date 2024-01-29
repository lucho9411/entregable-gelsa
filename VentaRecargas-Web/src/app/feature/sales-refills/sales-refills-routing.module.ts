import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RefillsComponent } from './refills/refills.component';
import { AuthGuard } from 'src/app/config/guards/auth.guard';

const routes: Routes = [
  {
    path:'', component: RefillsComponent, canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalesRefillsRoutingModule { }
