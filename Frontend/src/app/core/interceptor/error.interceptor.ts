import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { AlertService } from '../services/alert.service';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
    const router = inject(Router);
      const alertService = inject(AlertService);

    return next(req).pipe(
        catchError((error: HttpErrorResponse) => {
            let errorMessage = 'Ocorreu um erro inesperado. Tente novamente mais tarde.';

            // Verifica se é um erro de conexão/cliente ou do servidor
            if (error.error instanceof ErrorEvent) {
                // Erro no lado do cliente (ex: sem internet)
                errorMessage = `Erro de conexão: ${error.error.message}`;
            } else {
                // Erro retornado pelo Backend (Spring Boot)
                // Tentamos pegar a mensagem que vem no corpo do JSON do Spring
                const serverMessage = error.error?.message || error.error || 'Erro desconhecido';
                errorMessage = serverMessage;

            }
            console.error('Horus Error Interceptor:', error);

            if (error.status !== 0) {
                alertService.error(errorMessage);
            }

            // Re-lança o erro para que o componente também saiba que falhou (se precisar parar um loading, por exemplo)
            return throwError(() => error);
        })
    );
};