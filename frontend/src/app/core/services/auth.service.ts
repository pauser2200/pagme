import { Injectable, inject, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { isPlatformBrowser } from '@angular/common';
import { environment } from '../../../environments/environment.local';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  private platformId = inject(PLATFORM_ID);

  private apiUrl = `${environment.apiUrl}/pagme`;
  private tokenKey = 'pagme_token';

  // --- MUDANÇA PRINCIPAL ---
  // Inicializa o Subject já verificando se tem token salvo.
  // Assim ele nasce TRUE se o usuário já estiver logado.
  private isLoggedInSubject = new BehaviorSubject<boolean>(this.hasTokenStored());

  private currentUserSubject = new BehaviorSubject<any>(null);

  public isLoggedIn$ = this.isLoggedInSubject.asObservable();
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor() {
    // Se quiser recuperar dados do usuário ao dar F5, faça aqui
    // Ex: decodificar o token para pegar o nome
  }

  // Método auxiliar para checar o token de forma segura (SSR safe)
  private hasTokenStored(): boolean {
    if (isPlatformBrowser(this.platformId)) {
      return !!localStorage.getItem(this.tokenKey);
    }
    return false;
  }

  login(credentials: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, credentials).pipe(
      tap(response => {
        // Ajuste 'response.token' conforme seu JSON de retorno (pode ser accessToken)
        if (response && response.token) {
          this.setToken(response.token);
          this.isLoggedInSubject.next(true);

          if (response.user) {
            this.currentUserSubject.next(response.user);
          }
        }
      })
    );
  }

  logout() {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem(this.tokenKey);
    }
    this.isLoggedInSubject.next(false);
    this.currentUserSubject.next(null);
  }

  private setToken(token: string) {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem(this.tokenKey, token);
    }
  }

  getToken(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem(this.tokenKey);
    }
    return null;
  }


  // O Guard chama este método. Agora ele checa diretamente a fonte da verdade (storage)
  // ou o valor atual do Subject, garantindo que o F5 funcione.
  isLoggedIn(): boolean {
    // Prioriza checar o Subject, mas se estiver false, confirma no storage por garantia
    return this.isLoggedInSubject.value || this.hasTokenStored();
  }
}
