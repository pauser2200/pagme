import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { Devedor } from '../../models/devedor.model';
import { DevedorService } from '../../core/services/devedor.service';
import { StatusBadgeComponent } from "../../shared/components/status-badge/status-badge.component";
import { ModalDevedorComponent } from './components/modal-devedor/modal-devedor.component';

@Component({
  selector: 'app-devedores',
  standalone: true,
  imports: [CommonModule, PageHeaderComponent, StatusBadgeComponent, ModalDevedorComponent],
  templateUrl: './devedores.component.html',
  styleUrl: './devedores.component.scss'
})
export class DevedoresComponent implements OnInit {

  listaDevedores: Devedor[] = [];
  showModal = false;

  constructor(private devedorService: DevedorService) {}

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados() {
    this.devedorService.listar().subscribe({
      next: (dados) => {
        this.listaDevedores = dados;
      },
      error: (err) => console.error('Erro ao buscar devedores', err)
    });
  }

  abrirModal() {
    this.showModal = true;
  }

  fecharModal() {
    this.showModal = false;
  }
}