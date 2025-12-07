import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalComponent } from './modal.component';

@Component({
  selector: 'app-modal-confirmacao',
  standalone: true,
  imports: [CommonModule, ModalComponent],
  template: `
    <app-modal [isOpen]="visible" [title]="titulo" (close)="cancelar()">
      
      <div class="text-center p-3">
        <i class="bi bi-exclamation-triangle text-warning fs-1 mb-3"></i>
        <p class="text-light fs-5">{{ mensagem }}</p>
        <p class="text-light small" *ngIf="subtitulo">{{ subtitulo }}</p>
      </div>

      <div class="d-flex justify-content-center gap-3 mt-3">
        <button type="button" class="btn btn-outline-secondary px-4" (click)="cancelar()">
          Cancelar
        </button>
        <button type="button" class="btn btn-danger px-4" (click)="confirmar()">
          {{ textoBotaoConfirmar }}
        </button>
      </div>

    </app-modal>
  `
})
export class ModalConfirmacaoComponent {
  @Input() visible: boolean = false;
  @Output() visibleChange = new EventEmitter<boolean>();
  
  // Dados personalizáveis
  @Input() titulo: string = 'Confirmar Exclusão';
  @Input() mensagem: string = 'Tem certeza que deseja realizar esta ação?';
  @Input() subtitulo: string = '';
  @Input() textoBotaoConfirmar: string = 'Sim, Excluir';

  // Evento disparado quando o usuário clica em SIM
  @Output() onConfirmar = new EventEmitter<void>();

  cancelar() {
    this.visible = false;
    this.visibleChange.emit(false);
  }

  confirmar() {
    this.onConfirmar.emit(); // Avisa o pai "Pode apagar!"
    this.cancelar(); // Fecha o modal
  }
}