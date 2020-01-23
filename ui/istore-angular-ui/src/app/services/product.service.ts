import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Product } from '../models/Product'
import { API_GATEWAY_HOST } from '../app.constants'

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  getProducts():Observable<Product[]> {
    return this.http.get<Product[]>(`${API_GATEWAY_HOST}/catalog-service/product`);
  }
}
