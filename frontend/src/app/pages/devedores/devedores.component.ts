import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { Usuario as Devedor } from '../../models/usuario.model';
import { DevedorService } from '../../services/devedor.service';
import { StatusBadgeComponent } from "../../shared/components/status-badge/status-badge.component";
import { ModalDevedorComponent } from './components/modal-devedor/modal-devedor.component';
import { AlertComponent } from "../../shared/components/alert/alert.component";
import { ModalConfirmacaoComponent } from "../../shared/components/modal/modal-confirmacao.component";
import { AlertService } from '../../core/services/alert.service';

@Component({
  selector: 'app-devedores',
  standalone: true,
  imports: [CommonModule, PageHeaderComponent, StatusBadgeComponent, ModalDevedorComponent, AlertComponent, ModalConfirmacaoComponent],
  templateUrl: './devedores.component.html',
  styleUrl: './devedores.component.scss'
})
export class DevedoresComponent implements OnInit {
  [x: string]: any;

  listaDevedores: Devedor[] = [];
  showModal = false;
  showModalExclusao = false;
  devedorParaExcluir: Devedor | null = null;

  constructor(private devedorService: DevedorService, private alertaService: AlertService) { }

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados() {
    this.devedorService.listar().subscribe({
      next: (dados) => {
        this.listaDevedores = dados;
      }
    });
  }

  prepararExclusao(devedor: Devedor) {
    this.devedorParaExcluir = devedor; 
    this.showModalExclusao = true;     
  }

  // O Modal de Confirmação chama ESTE método se o usuário disser "SIM"
  efetivarExclusao() {
    if (this.devedorParaExcluir) {
      this.devedorService.excluir(this.devedorParaExcluir.id).subscribe({
        next: () => {
          this.alertaService.success('Devedor inativado com sucesso!');
          this.carregarDados(); 
          this.devedorParaExcluir = null; 
        }
      });
    }
  }

  abrirModal() {
    this.showModal = true;
  }

  fecharModal() {
    this.showModal = false;
  }
}