import { Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { ProdutosComponent } from './pages/produtos/produtos.component';
import { DevedoresComponent } from './pages/devedores/devedores.component';
import { LoginComponent } from './pages/login/login.component';
import { authGuard, loginGuard } from './core/services/auth.guard';

export const routes: Routes = [
  // Defina as rotas
  {
    path: '', // Rota padrão (ex: http://localhost:4200/)
    component: HomeComponent,
    title: 'Página Inicial',
    canActivate: [authGuard] // Protege a rota com a guarda de autenticação
  },
  {
    path: 'produtos', // Rota de produtos (ex: .../produtos)
    component: ProdutosComponent,
    title: 'Produtos',
    canActivate: [authGuard] // Protege a rota com a guarda de autenticação
  },
  {
    path: 'sobre', // Rota "Devedores" (ex: .../sobre)
    component: DevedoresComponent,
    title: 'Sobre Nós',
    canActivate: [authGuard]
  },
  {
    path: 'login', component: LoginComponent, canActivate: [loginGuard], title: 'Login'
  },
 
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  }
];