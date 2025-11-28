import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Devedor } from '../../models/devedor.model';

@Injectable({
  providedIn: 'root'
})
export class DevedorService {

  // Mock de dados (Simulando o Banco de Dados)
  private mockDevedores: Devedor[] = [
    { id: 1, nome: 'Carlos Souza', telefone: '(11) 99999-1234', status: 'ATIVO', totalDevido: 4500.00 },
    { id: 2, nome: 'Maria Oliveira', telefone: '(21) 98888-5678', status: 'ATIVO', totalDevido: 2100.00 },
    { id: 3, nome: 'João da Silva', email: 'joao@email.com', status: 'BLOQUEADO', totalDevido: 150.00 },
    { id: 4, nome: 'Ana Pereira', telefone: '(31) 97777-1122', status: 'INATIVO', totalDevido: 0.00 },
  ];

  constructor() { }

  // Retorna a lista
  listar(): Observable<Devedor[]> {
    return of(this.mockDevedores);
  }

  // Aqui entrarão os métodos criar(), atualizar() e deletar() depois
}