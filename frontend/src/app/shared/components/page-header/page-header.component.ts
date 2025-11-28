import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-page-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './page-header.component.html',
  styleUrls: ['./page-header.component.scss']
})
export class PageHeaderComponent {
  // Recebe o título como parâmetro
  @Input() title: string = '';
  
  // Recebe o subtítulo (opcional)
  @Input() subtitle: string = '';
}