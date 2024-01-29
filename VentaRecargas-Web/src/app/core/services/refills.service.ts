import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Refills } from '../interfaces/refills';

@Injectable({
  providedIn: 'root'
})
export class RefillsService {

  api = environment.api;
  refill = environment.refill;
  operators = environment.operators;

  constructor(
    private readonly http: HttpClient
  ) { }

  listOperators(): Observable<any> {
    return this.http.get<any>(
      this.api.concat(this.operators.concat('listar'))).pipe(
      map((response: any) => {
          return response;
      }),catchError(error => {
          return throwError(error);
      })
    );
  }

  saveRefill(data: Refills): Observable<any> {
    return this.http.post<any>(
      this.api.concat(this.refill.concat('agregar')), data).pipe(
      map((response: any) => {
          return response;
      }),catchError(error => {
          return throwError(error);
      })
    );
  }

  listRefill(): Observable<any> {
    return this.http.get<any>(
      this.api.concat(this.refill.concat('listar'))).pipe(
      map((response: any) => {
          return response;
      }),catchError(error => {
          return throwError(error);
      })
    );
  }
}
