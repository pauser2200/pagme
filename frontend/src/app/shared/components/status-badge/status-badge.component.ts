import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-status-badge',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './status-badge.component.html',
  styleUrl: './status-badge.component.scss'
})
export class StatusBadgeComponent {

  // Recebe o texto do status (ex: 'ATIVO', 'BLOQUEADO')
  @Input() status: string = '';

  // Lógica para decidir a cor baseada no status
  get statusClass(): string {
    switch (this.status) {
      case 'ATIVO':
        return 'bg-success-subtle text-success border-success-subtle';
      
      case 'INATIVO':
        return 'bg-danger-subtle text-danger border-danger-subtle';
      
      case 'PENDENTE': // Exemplo extra se precisar no futuro
        return 'bg-warning-subtle text-warning border-warning-subtle';

      default:
        return 'bg-light text-dark border-light'; // Cor padrão se não casar com nada
    }
  }
}