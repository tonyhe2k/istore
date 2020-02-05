import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { API_GATEWAY_HOST } from '../../app.constants'
import {map} from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class AuthAPIService {

  constructor(
    private httpClient: HttpClient
  ) { }

  doAuth(email, password):Observable<any> {
    return this.httpClient.post<any>(`${API_GATEWAY_HOST}/customer-service/user/login`, {email, password}, {observe: 'response'})
  }

  register(email, password, username, firstName, lastName):Observable<any> {
    return this.httpClient.post<any>(`${API_GATEWAY_HOST}/customer-service/user`, {email, password, username, firstName, lastName}, {observe: 'response'})
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem('authenticatedUser')
  }

  getAuthenticatedToken() {
    return sessionStorage.getItem('token')
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('authenticatedUser')
    return !(user === null)
  }

  logout() {
    sessionStorage.removeItem('authenticatedUser')
    sessionStorage.removeItem('token')
  }
}
