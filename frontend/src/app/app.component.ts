import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './layout/header/header.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { AuthService } from './core/services/auth.service'; 
import { Router } from '@angular/router';


import { SidebarService } from './core/services/sidebar.service';
import { Observable } from 'rxjs';

import { Idle, DEFAULT_INTERRUPTSOURCES } from '@ng-idle/core';
import { Keepalive } from '@ng-idle/keepalive';

@Component({
  selector: 'app-root',
  standalone: true, 
  imports: [CommonModule, RouterOutlet, HeaderComponent, SidebarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit{
  
  public isSidebarOpen$: Observable<boolean>;
  public layoutVisible$: Observable<boolean>; 
  isIdleWarningVisible: boolean = false;
  idleCountdown: number = 0;

  constructor(private sidebarService: SidebarService, private idle: Idle, private keepalive: Keepalive
    , private authService: AuthService, private router: Router
  )  {
    this.isSidebarOpen$ = this.sidebarService.isOpen$;
    this.layoutVisible$ = this.sidebarService.layoutVisible$;

    // O usuário será considerado "inativo" (idle) após 900 segundos (15 minutos)
    idle.setIdle(60); 

    // Definir o tempo de "aviso" (grace period)
    // Após os 15 min, o usuário tem mais 300 segundos (5 minutos) de aviso.
    // Se ele não interagir nesse período, o onTimeout é disparado.
    idle.setTimeout(30); 

    // Definir as fontes de interrupção
    // O que "reinicia" o timer? O padrão são cliques, movimentos do mouse,
    // digitação, scroll e eventos de toque.
    idle.setInterrupts(DEFAULT_INTERRUPTSOURCES);
    
  }

  ngOnInit(): void {

     if (this.authService.isLoggedIn()) {
         this.startWatching();
       }
  }

  // Método para o botão "Abrir"
  toggleSidebarApp(): void {
    this.sidebarService.toggle();
  }

  startWatching() {
    // ---- Eventos do Idle ----

    // Disparado quando o usuário fica inativo (após os 15 min)
    this.idle.onIdleStart.subscribe(() => {
      console.log('Usuário ficou inativo. Mostrando modal de aviso...');
      this.isIdleWarningVisible = true; // Mostre seu modal de aviso
    });

    // Disparado quando o tempo limite final (5 min de aviso) se esgota
    this.idle.onTimeout.subscribe(() => {
      console.log('Timeout! Deslogando o usuário.');
      this.isIdleWarningVisible = false; // Esconda o modal
      
      // AQUI É A AÇÃO DE LOGOUT
       this.authService.logout();
       this.router.navigate(['/login']);
    });

    // Disparado quando o usuário volta a ser ativo (remove o status "idle")
    this.idle.onIdleEnd.subscribe(() => {
      console.log('Usuário voltou a ser ativo.');
      this.isIdleWarningVisible = false; // Esconda o modal
      this.idleCountdown = 0;
    });

    // Disparado a cada segundo durante o período de "aviso" (os 5 min)
    this.idle.onTimeoutWarning.subscribe((countdown) => {
      this.idleCountdown = countdown;
      console.log('Aviso de timeout em ' + countdown + ' segundos!');
      // Use isso para mostrar um contador no seu modal (ex: "Sessão expirando em 299s...")
    });

    // Isso "pinga" seu backend para que a sessão do servidor não expire
    // enquanto o usuário estiver ativo no frontend.
    this.keepalive.interval(1 * 60); 
    // this.keepalive.request('url-do-seu-endpoint-de-ping'); // Defina seu endpoint
    // this.keepalive.onPing.subscribe(() => console.log('Ping enviado.'));

    // Inicia o monitoramento
    this.idle.watch();
    console.log('Monitoramento de inatividade iniciado.');
  }
}