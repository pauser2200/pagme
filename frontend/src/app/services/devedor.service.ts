import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario as Devedor, Usuario } from '../models/usuario.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DevedorService {
   private apiUrl = `${environment.apiUrl}/devedores`;

  constructor(private http: HttpClient) {}

  listar(): Observable<Devedor[]> {
    return this.http.get<Devedor[]>(`${this.apiUrl}/listar`);
  }

  // Esse é o método que o Modal vai chamar ao dar "Submit"
  salvar(devedor: any): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/`, devedor);
  }

  excluir(id:number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}