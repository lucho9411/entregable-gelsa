import { Component } from '@angular/core';
import { Refills } from 'src/app/core/interfaces/refills';
import { RefillsService } from 'src/app/core/services/refills.service';
import { MessageService } from 'primeng/api';
import { Responses } from 'src/app/core/interfaces/responses';

@Component({
  selector: 'app-list-refills',
  templateUrl: './list-refills.component.html',
  styleUrls: ['./list-refills.component.scss']
})
export class ListRefillsComponent {

  recargas: Refills[] = [];

  dineroTotal = 0;

  constructor(
    private readonly refillService: RefillsService,
    private messageService: MessageService
  ){
    this.listRefills();
  }

  listRefills(): void {
    this.refillService.listRefill().subscribe((response: any) => {
      const message: Responses = response[0];
      if (message.Msg.Code === 200) {
        this.recargas = response[1];
        this.recargas.forEach(element => {
          this.dineroTotal = this.dineroTotal + element.Precio;
        });
      }
      else if (message.Msg.Code === 404) {
        this.recargas = [];
      }
      else {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error en el sistema' })
      }
    }, error =>{
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error en el sistema' })
      console.log('Error:: ', error);
    });
  }

}
