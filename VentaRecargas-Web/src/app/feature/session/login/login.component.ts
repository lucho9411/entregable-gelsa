import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionService } from 'src/app/core/services/session.service';
import { LoginDTO } from '../../../core/interfaces/session';
import { Responses } from 'src/app/core/interfaces/responses';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  form: FormGroup;

  errorUsuario = false;
  errorClave = false;

  constructor(
    private readonly formBuilder:FormBuilder,
    private readonly sessionService: SessionService,
    private messageService: MessageService,
    private router:Router
  ){
    this.createForm();
  }

  createForm(): void {
    this.form = this.formBuilder.group({
      usuario: [undefined, [Validators.required]],
      clave: [undefined, [Validators.required]]
    })
  }

  validar(input: string): void {
    if (input === 'usuario') {
      this.errorUsuario = true;
    }
    else {
      this.errorClave = true;
    }
  }

  iniciarSesion(): void {
    const data: LoginDTO = {
      User: this.form.get('usuario')?.value,
      Password: this.form.get('clave')?.value
    }
    this.sessionService.iniciarSesion(data).subscribe((response: any) => {
      const message: Responses = response[0];
      if (message.Msg.Code === 404) {
        this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: 'Usuario y/o clave inválidos' })
      }
      else if (message.Msg.Code === 200) {
        this.sessionService.saveSessionInfo(data.User, response[1].token);
        this.router.navigateByUrl('/recargas');
      }
      else {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error en el sistema' })
      }
    }, error => {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error en el sistema' })
      console.log('Error!');
    });
  }
}
