import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { AuthAPIService } from './auth-api.service';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterBasicAuthServiceService implements HttpInterceptor{

  constructor(
    private authAPIService : AuthAPIService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {

    let basicAuthHeader = this.authAPIService.getAuthenticatedToken()
    let username = this.authAPIService.getAuthenticatedUser()

    if (basicAuthHeader && username) {
      request = request.clone({
        setHeaders : {
          Authorization : basicAuthHeader
        }
      })
    }

    return next.handle(request)
  }
}
