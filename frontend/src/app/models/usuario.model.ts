// src/app/models/user.model.ts

export interface Usuario {
  id: number;
  email: string;
  nome: string;
  telefone: string;
  status: string;
  criadoEm: Date;
  role: string;
}