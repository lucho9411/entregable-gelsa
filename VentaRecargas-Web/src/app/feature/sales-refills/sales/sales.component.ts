import { Component, OnInit } from '@angular/core';
import { Operators, Refills } from 'src/app/core/interfaces/refills';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RefillsService } from 'src/app/core/services/refills.service';
import { Responses } from 'src/app/core/interfaces/responses';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.scss']
})
export class SalesComponent implements OnInit{

  operadores: Operators[];

  form: FormGroup;

  constructor(
    private readonly formBuilder:FormBuilder,
    private readonly refillService: RefillsService,
    private messageService: MessageService
  ){
    this.createForm();
  }

  createForm(): void {
    this.form = this.formBuilder.group({
      operador: [undefined, [Validators.required]],
      precio: [0, [Validators.required]],
      numero: [undefined, [Validators.required]],
      confirmarNumero: [undefined, [Validators.required]]
    })
  }

  ngOnInit(): void {
    this.listOperators();
  }

  listOperators(): void {
    this.refillService.listOperators().subscribe((response: any) => {
      const message: Responses = response[0];
      if (message.Msg.Code === 200) {
        this.operadores = response[1];
      }
      else if (message.Msg.Code === 404) {
        this.operadores = [];
      }
      else {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error en el sistema' })
      }
    }, error => {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error en el sistema' })
      console.log('Error!', error);
    });
  }

  completarVenta(): void {
    let num = this.form.get('numero')?.value;
    num = num.split(' ').join('');
    num = num.split('(').join('');
    num = num.split(')').join('');
    num = num.split('-').join('');
    num = num.split(',').join('');
    const data: Refills = {
      NumeroTelefono: num,
      Precio: this.form.get('precio')?.value,
      Vendedor: {
        IdUser: 1,
        Name: String(sessionStorage.getItem('User')),
        Status: 1
      },
      Operador: this.form.get('operador')?.value
    };
    this.refillService.saveRefill(data).subscribe((response: any) => {
      const message: Responses = response[0];
      if (message.Msg.Code === 200) {
        this.messageService.add({ severity: 'success', summary: 'Venta Exisotsa', detail: 'Venta exitosa!' })
        this.form.reset();
      }
      else {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error en el sistema' })
      }
    }, error => {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error en el sistema' })
      console.log('Error:: ', error);
    });
  }

}
