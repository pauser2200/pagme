// src/app/services/product.service.ts

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produto } from '../models/produto.model'; 

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'https://fakestoreapi.com/products'; 

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.apiUrl);
  }
}