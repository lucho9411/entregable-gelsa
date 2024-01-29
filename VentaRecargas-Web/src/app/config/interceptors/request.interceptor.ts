import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, finalize, throwError } from 'rxjs';
import { LoaderService } from 'src/app/core/services/loader.service';
import { Router } from '@angular/router';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

  constructor(
    private readonly loaderService: LoaderService,
    private readonly router: Router
  ) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let requestClone: any;
    this.loaderService.show();
    if (sessionStorage.getItem('User') && sessionStorage.getItem('Token')) {
      const headers = new HttpHeaders({
        'Authorization': 'Bearer ' + sessionStorage.getItem('Token')
      });
      requestClone = request.clone({
        headers
      });

      return next.handle(requestClone).pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 401) {
            this.router.navigateByUrl('/');
            this.loaderService.hide();
          }
          this.loaderService.hide();
          return throwError( error );
  
        }), finalize (() => {
          this.loaderService.hide();
        })
      );
    }
    return next.handle(request).pipe(
      finalize (() => {
        this.loaderService.hide();
      })
);
  }
}
