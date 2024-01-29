import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SalesRefillsRoutingModule } from './sales-refills-routing.module';
import { RefillsComponent } from './refills/refills.component';
import { SharedModule } from 'src/app/shared/shared/shared.module';
import { SalesComponent } from './sales/sales.component';
import { ListRefillsComponent } from './list-refills/list-refills.component';


@NgModule({
  declarations: [
    RefillsComponent,
    SalesComponent,
    ListRefillsComponent
  ],
  imports: [
    CommonModule,
    SalesRefillsRoutingModule,
    SharedModule
  ]
})
export class SalesRefillsModule { }
