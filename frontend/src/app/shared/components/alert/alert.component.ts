import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlertService } from '../../../core/services/alert.service';
import { NavigationStart, Router } from '@angular/router';

@Component({
    selector: 'app-alert',
    standalone: true,
    imports: [CommonModule],
    template: `
    @if (alert$ | async; as alert) {
      <div class="alert alert-dismissible fade show m-4" 
           [ngClass]="'alert-' + alert.type" 
           role="alert">
        
            <strong>{{ alert.message }}</strong>  
        
        <button type="button" class="btn-close" (click)="clear()" aria-label="Close"></button>
      </div>
    }
  `
})
export class AlertComponent {
    private alertService = inject(AlertService);
    private router = inject(Router);

    alert$ = this.alertService.alert$;

    constructor() {
        // Opcional: Limpar o erro automaticamente ao mudar de página
        this.router.events.subscribe(event => {
            if (event instanceof NavigationStart) {
                this.alertService.clear();
            }
        });
    }

    clear() {
        this.alertService.clear();
    }
}