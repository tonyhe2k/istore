import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Order } from '../models/Order'
import { API_GATEWAY_HOST } from '../app.constants'

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http:HttpClient) { }

  executeOrder(order:Order):Observable<Order> {
    return this.http.post<Order>(`${API_GATEWAY_HOST}/order-service/order`, order);
  }
}
