import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms'; 
import { ModalComponent } from '../../../../shared/components/modal/modal.component';
import { DevedorService } from '../../../../services/devedor.service';
import { AlertComponent } from '../../../../shared/components/alert/alert.component';
import { AlertService } from '../../../../core/services/alert.service';

@Component({
  selector: 'app-modal-devedor',
  standalone: true,
  imports: [CommonModule, ModalComponent, FormsModule, ReactiveFormsModule], 
  templateUrl: './modal-devedor.component.html',
  styleUrl: './modal-devedor.component.scss'
})
export class ModalDevedorComponent implements OnInit {

  @Input() visible: boolean = false;
  @Output() visibleChange = new EventEmitter<boolean>();
  @Output() onSalvar = new EventEmitter<void>();
  
  modalDevedorForm!: FormGroup;

 
  constructor(private devedorService: DevedorService,private fb: FormBuilder, private alertService: AlertService) {}

ngOnInit(): void {
    this.inicializarFormulario();
  }


  fechar() {
    this.visible = false;
    this.visibleChange.emit(this.visible);
    this.inicializarFormulario();
  }

  

  salvar() {

    if (this.modalDevedorForm.invalid) {
      this.modalDevedorForm.markAllAsTouched(); // Faz os erros aparecerem na tela (vermelho)
      return;
    }
    const formValues = this.modalDevedorForm.value;

    // Se não tiver email, cria um fake baseado no nome para não quebrar o banco
    const emailFinal = formValues.email || 
      `${formValues.nome.toLowerCase().replace(/\s/g, '')}@pagme.local`;

    const payloadBackEnd = {
      nome: formValues.nome,
      telefone: formValues.telefone,
      email: emailFinal,
      username: emailFinal.split('@')[0], 
      password: 'mudar123', 
      role: 'USER' 
    };

    this.devedorService.salvar(payloadBackEnd).subscribe({
      next: () => {
        this.alertService.success('Devedor salvo com sucesso!');
        this.onSalvar.emit();
        this.fechar();
      }
    });
  }

  inicializarFormulario() {
    this.modalDevedorForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      telefone: ['', Validators.required],
      email: ['', [Validators.email]] 
    });
  }
}