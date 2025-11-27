// src/app/layout/header/header.component.ts (ATUALIZADO)

import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { AuthService } from '../../core/services/auth.service';
import { User } from '../../models/user.model'; // 1. Importe o User

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  // 2. Precisamos dos dois observables
  public isLoggedIn$: Observable<boolean>;
  public currentUser$: Observable<User | null>;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
    // 3. Obtém os novos observables do serviço
    this.isLoggedIn$ = this.authService.isLoggedIn$;
    this.currentUser$ = this.authService.currentUser$;
  }

  onLogout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}