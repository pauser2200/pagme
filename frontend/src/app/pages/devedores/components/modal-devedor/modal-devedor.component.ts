import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalComponent } from '../../../../shared/components/modal/modal.component'; // Ajuste o caminho se necessário

@Component({
  selector: 'app-modal-devedor',
  standalone: true,
  imports: [CommonModule, ModalComponent], // Importamos o Modal Genérico aqui
  templateUrl: './modal-devedor.component.html',
  styleUrl: './modal-devedor.component.scss'
})
export class ModalDevedorComponent {

  // Recebe do pai se deve aparecer ou não
  @Input() visible: boolean = false;

  // Avisa o pai para fechar (mudando a variável lá)
  @Output() visibleChange = new EventEmitter<boolean>();

  fechar() {
    this.visible = false;
    this.visibleChange.emit(this.visible); // Avisa o pai que fechou
  }

  salvar() {
    console.log('Salvando novo devedor...');
    // Aqui você chamaria o serviço depois
    this.fechar();
  }
}