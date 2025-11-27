import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { AuthService } from '../../core/services/auth.service';
import { SidebarService } from '../../core/services/sidebar.service';
import { AlertComponent } from "../../shared/componentes/alert.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AlertComponent
],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit, OnDestroy {

  public loginForm: FormGroup;
  public isLoading = false;
  public errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private sidebarService: SidebarService,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required], , Validators.minLength(14)],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // Ao entrar, esconde o layout principal
  ngOnInit(): void {
    // Adiciona o 'setTimeout' para "atrasar" a mudança
    // para o próximo ciclo de detecção.
    setTimeout(() => {
      this.sidebarService.setLayoutVisible(false);
    }, 0);
  }

  ngOnDestroy(): void {
    // Envolve a mudança em um setTimeout
    // para garantir que ela rode no próximo ciclo
    // de detecção de mudanças, após a navegação
    // e a destruição serem concluídas.
    setTimeout(() => {
      this.sidebarService.setLayoutVisible(true);
    }, 0);
  }

  // Método de Submit
  onSubmit(): void {
    if (this.loginForm.invalid) return;

    this.isLoading = true;  // Bloqueia o botão
    this.errorMessage = ''; // Limpa erros anteriores

    this.authService.login(this.loginForm.value).subscribe({
      next: () => {
        // O redirecionamento acontece, não precisa mudar o isLoading para false
        // pois a página vai mudar.
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.isLoading = false; // Libera o botão novamente

      }
    });
  }
}