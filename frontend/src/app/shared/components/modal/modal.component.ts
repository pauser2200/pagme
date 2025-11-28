import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.scss'
})
export class ModalComponent {

  @Input() title: string = 'Título do Modal';
  @Input() isOpen: boolean = false;
  
  // Emite um evento quando o usuário clica em fechar ou no fundo escuro
  @Output() close = new EventEmitter<void>();

  onClose() {
    this.close.emit();
  }
  
  // Impede que o clique dentro do cartão feche o modal (Event Bubbling)
  stopPropagation(event: Event) {
    event.stopPropagation();
  }
}