import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-refills',
  templateUrl: './refills.component.html',
  styleUrls: ['./refills.component.scss']
})
export class RefillsComponent {

  items: MenuItem[] | undefined;

  position: string = 'top';

  opt1 = true;
  opt2 = false;

  positionOptions = [
    {
        label: 'Bottom',
        value: 'bottom'
    },
    {
        label: 'Top',
        value: 'top'
    },
    {
        label: 'Left',
        value: 'left'
    },
    {
        label: 'Right',
        value: 'right'
    }
];

  constructor(
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private router:Router
  ){
    this.items = [
      {
          label: 'Recargar',
          icon: 'http://localhost:4200/assets/phone.svg',
          pTooltip: 'Recargar',
          option: 1
      },
      {
          label: 'Listado de Recargas',
          icon: 'http://localhost:4200/assets/list.svg',
          pTooltip: 'Listado de Recargas',
          option: 2
      },
      {
          label: 'Cerrar Sesión',
          icon: 'http://localhost:4200/assets/logout.svg',
          pTooltip: 'Cerrar Sesión',
          option: 3
      }
  ];
  }

  opcion(item: number): void {
    if (item === 1) {
      this.opt1 = true;
      this.opt2 = false;
    }
    else if (item === 2) {
      this.opt1 = false;
      this.opt2 = true;
    }
    else if (item === 3) {
      this.logout();
    }
  }

  logout(): void {
    this.confirmationService.confirm({
      message: 'Seguro desea cerrar la sesión actual?',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        sessionStorage.clear();
        this.messageService.add({ severity: 'info', summary: 'Un momento por favor!', detail: 'Cerrando su sesión actual', life: 3000 });
        this.router.navigateByUrl('/');
      },
      reject: () => {
          this.messageService.add({ severity: 'error', summary: 'Error!', detail: 'Error al cerrar su sesión', life: 3000 });
      }
  });
  }

}
