// src/app/services/auth.guard.ts

import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';
import { AuthService } from './auth.service';
import { PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

/**
 * Esta é a nossa "Guarda Principal"
 * Ela bloqueia o acesso ao app se o usuário NÃO estiver logado.
 */
export const authGuard: CanActivateFn = (route, state) => {

  // Injeta os serviços que precisamos
  const authService = inject(AuthService);
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  if (!isPlatformBrowser(platformId)) {
    return true;
  }

  const logged = authService.isLoggedIn();
  console.log('Tentando acessar rota:', state.url, '| Está logado?', logged);

  // O AuthService.isLoggedIn() é síncrono, perfeito para isso
  if (logged) {
    return true; // Usuário logado, pode acessar a rota
  }

  // Usuário deslogado:
  router.navigate(['/login']); // Redireciona para o login
  return false; // Bloqueia a rota atual
};

/**
 * Esta é a nossa "Guarda de Login"
 * Ela bloqueia o acesso à página de login se o usuário JÁ estiver logado.
 */
export const loginGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const router = inject(Router);

  const logged = authService.isLoggedIn();
  console.log('Tentando acessar rota:', state.url, '| Está logado?', logged);

  if (logged) {
    // Usuário já logado:
    router.navigate(['/']); // Redireciona para a Home
    return false; // Bloqueia o acesso ao /login
  }

  // Usuário deslogado, pode acessar o login
  return true;
};