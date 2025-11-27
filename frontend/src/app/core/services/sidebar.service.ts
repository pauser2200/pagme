// src/app/services/sidebar.service.ts (VERSÃO FINAL LIMPA)

import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SidebarService {

  private isOpen = new BehaviorSubject<boolean>(true);
  public isOpen$ = this.isOpen.asObservable();

  private layoutVisible = new BehaviorSubject<boolean>(true);
  public layoutVisible$ = this.layoutVisible.asObservable();

  constructor() { }

  public toggle(): void {
    this.isOpen.next(!this.isOpen.value);
  }

  public setLayoutVisible(isVisible: boolean): void {
    this.layoutVisible.next(isVisible);
  }
  
  
}