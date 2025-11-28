import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { AlertService } from '../../core/services/alert.service';
import { AlertComponent } from "../../shared/components/alert.component";
import { PageHeaderComponent } from "../../shared/components/page-header/page-header.component";

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [AlertComponent, CommonModule, PageHeaderComponent], 
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

    // 1. Dados dos Cards do Topo (Resumo)
    stats = {
        totalReceber: 12500.50,  
        venceEsteMes: 3200.00,   
        pessoasDevem: 8,         
        cartoesUsados: 3         
    };

    // 2. Dados da Tabela (Próximos a Vencer)
    proximosVencimentos = [
        { devedor: 'João da Silva', valor: 150.00, data: '10/12/2025', cartao: 'Nubank Final 1234' },
        { devedor: 'Maria Oliveira', valor: 450.50, data: '12/12/2025', cartao: 'Inter Final 9988' },
        { devedor: 'Carlos Souza', valor: 1200.00, data: '15/12/2025', cartao: 'XP Visa Infinite' },
        { devedor: 'Ana Pereira', valor: 89.90, data: '20/12/2025', cartao: 'Nubank Final 1234' },
    ];

    // 3. Dados da Lista Lateral (Quem deve mais)
    topDevedores = [
        { nome: 'Carlos Souza', total: 4500.00 },
        { nome: 'Maria Oliveira', total: 2100.00 },
        { nome: 'Pedro Santos', total: 1800.00 },
        { nome: 'Ana Pereira', total: 950.00 },
    ];

    constructor(private alertas: AlertService) { }

    ngOnInit(): void {
        // Exemplo: Disparar um alerta de boas-vindas (opcional)
        // this.alertas.success('Bem-vindo ao Pagme! O sistema está pronto.');
    }
}