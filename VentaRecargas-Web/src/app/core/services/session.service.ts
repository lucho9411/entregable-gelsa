import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { LoginDTO } from '../interfaces/session';
import { Observable, catchError, map, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  api = environment.api;
  seguridad = environment.security;

  constructor(private readonly http: HttpClient) { }

  iniciarSesion(data: LoginDTO): Observable<any> {
    return this.http.post<any>(
      this.api.concat(this.seguridad.concat('login')), data).pipe(
      map((response: any) => {
          return response;
      }),catchError(error => {
          return throwError(error);
      })
    );
  }

  saveSessionInfo(user: string, token: string): void {
    sessionStorage.setItem('User', user);
    sessionStorage.setItem('Token', token);
  }
}
