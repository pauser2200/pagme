// src/app/models/user.model.ts

export interface Devedor {
  id: number;
  nome: string;
  email?: string;
  telefone?: string;
  status: 'ATIVO' | 'INATIVO' | 'BLOQUEADO';
  totalDevido?: number;
}