// src/app/pages/produtos/produtos.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

import { ProductService } from '../../services/produto.service';
import { Produto } from '../../models/produto.model';

@Component({
  standalone: true,
  selector: 'app-produtos',
  imports: [CommonModule],
  templateUrl: './produtos.component.html',
  styleUrl: './produtos.component.scss'
})
export class ProdutosComponent implements OnInit {


  public products$!: Observable<Produto[]>;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.products$ = this.productService.getProducts();
  }
}