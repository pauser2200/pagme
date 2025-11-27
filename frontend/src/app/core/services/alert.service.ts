import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface Alert {
  type: 'success' | 'danger' | 'warning' | 'info';
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private alertSubject = new BehaviorSubject<Alert | null>(null);
  public alert$ = this.alertSubject.asObservable();

  // Mostra um erro (vermelho)
  error(message: string) {
    this.alertSubject.next({ type: 'danger', message });
  }

  // Mostra sucesso (verde) - útil para "Salvo com sucesso"
  success(message: string) {
    this.alertSubject.next({ type: 'success', message });
  }

  // Limpa o alerta
  clear() {
    this.alertSubject.next(null);
  }
}