import { Component } from '@angular/core';
import { AlertService } from '../../core/services/alert.service';
import { AlertComponent } from "../../shared/componentes/alert.component";

@Component({
    selector: 'app-home',
    imports: [AlertComponent],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss'
})
export class HomeComponent {

    constructor(private alertas: AlertService) { }


}
