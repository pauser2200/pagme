import { Component, OnInit, HostListener, Inject, PLATFORM_ID } from '@angular/core'; // <--- Adicione Inject e PLATFORM_ID
import { CommonModule, isPlatformBrowser } from '@angular/common'; // <--- Adicione isPlatformBrowser
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
export class AppComponent implements OnInit {
  
  public isSidebarOpen$: Observable<boolean>;
  public layoutVisible$: Observable<boolean>; 
  
  // Variável local para rastrear o estado
  private isMenuOpen: boolean = true;

  isIdleWarningVisible: boolean = false;
  idleCountdown: number = 0;

  constructor(
    private sidebarService: SidebarService, 
    private idle: Idle, 
    private keepalive: Keepalive,
    private authService: AuthService, 
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object // <--- Injete o ID da plataforma
  ) {
    this.isSidebarOpen$ = this.sidebarService.isOpen$;
    this.layoutVisible$ = this.sidebarService.layoutVisible$;

    // Inscreva-se para manter a variável local atualizada
    this.isSidebarOpen$.subscribe(state => {
      this.isMenuOpen = state;
    });

    idle.setIdle(60); 
    idle.setTimeout(30); 
    idle.setInterrupts(DEFAULT_INTERRUPTSOURCES);
  }

  ngOnInit(): void {
     if (this.authService.isLoggedIn()) {
         this.startWatching();
     }
     
     // Verificação inicial segura
     this.checkScreenSize();
  }

  // Listener de redimensionamento
  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.checkScreenSize();
  }

  // --- CORREÇÃO AQUI ---
  private checkScreenSize() {
    // Só executa se estivermos no NAVEGADOR (Browser)
    if (isPlatformBrowser(this.platformId)) {
      
      // Agora é seguro usar 'window'
      if (window.innerWidth <= 768 && this.isMenuOpen) {
        this.sidebarService.toggle();
      }
      
    }
  }

  // Método para o botão "Abrir"
  toggleSidebarApp(): void {
    this.sidebarService.toggle();
  }

  startWatching() {
    // (Lógica do Idle mantida inalterada...)
    this.idle.onIdleStart.subscribe(() => {
      console.log('Usuário ficou inativo.');
      this.isIdleWarningVisible = true;
    });

    this.idle.onTimeout.subscribe(() => {
      console.log('Timeout!');
      this.isIdleWarningVisible = false;
       this.authService.logout();
       this.router.navigate(['/login']);
    });

    this.idle.onIdleEnd.subscribe(() => {
      this.isIdleWarningVisible = false;
      this.idleCountdown = 0;
    });

    this.idle.onTimeoutWarning.subscribe((countdown) => {
      this.idleCountdown = countdown;
    });

    this.keepalive.interval(1 * 60); 
    this.idle.watch();
  }
}