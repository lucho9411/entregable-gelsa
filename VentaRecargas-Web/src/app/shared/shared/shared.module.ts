import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { CardModule } from 'primeng/card';
import {SplitterModule} from 'primeng/splitter';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { DockModule } from 'primeng/dock';
import { TooltipModule } from 'primeng/tooltip';
import { DropdownModule } from 'primeng/dropdown';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputMaskModule } from 'primeng/inputmask';
import { TableModule } from 'primeng/table';
import { ConfirmPopupModule } from 'primeng/confirmpopup';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    InputGroupModule,
    InputGroupAddonModule,
    CardModule,
    SplitterModule,
    ButtonModule,
    FormsModule,
    ToastModule,
    DockModule,
    TooltipModule,
    DropdownModule,
    InputNumberModule,
    InputMaskModule,
    TableModule,
    ConfirmPopupModule,
    ReactiveFormsModule
  ],
  exports:[
    InputGroupModule,
    InputGroupAddonModule,
    CardModule,
    SplitterModule,
    ButtonModule,
    FormsModule,
    ToastModule,
    DockModule,
    TooltipModule,
    DropdownModule,
    InputNumberModule,
    InputMaskModule,
    TableModule,
    ConfirmPopupModule,
    ReactiveFormsModule
  ]
})
export class SharedModule { }
