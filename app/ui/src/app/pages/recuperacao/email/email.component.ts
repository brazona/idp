import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FieldEmailComponent} from "../../components/fields/email/field.email.component";
import {FlexModule} from "@angular/flex-layout";
import {ForgotComponent} from "../../components/buttons/forgot/forgot.component";
import {NgIf} from "@angular/common";
import {RouterLink, RouterOutlet} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../core/services/auth.service";
import {StorageService} from "../../../core/services/storage.service";

@Component({
  selector: 'app-email',
  standalone: true,
  imports: [
    FlexModule,
    ForgotComponent,
    NgIf,
    RouterLink,
    FieldEmailComponent
  ],
  templateUrl: './email.component.html',
  styleUrl: './email.component.scss'
})
export class EmailComponent implements OnInit{

  emailRequerid:boolean = false;
  usernameEmail:boolean = false;

  formularioRecuperar: FormGroup;
  formulario: FormControl;
  submitted:boolean;
  FIEL_EMAIL:string = 'email';

  @Output() public onRecuperar = new EventEmitter();

  constructor(private formBuilder: FormBuilder, private service: AuthService, private storageService: StorageService) {

    // **************************************************
    // Abaixo utilizamos o formBuilder para construir
    // vários FormControls que fazem parte do formulário.
    // Cada FormControl valida um input do formulário
    // **************************************************
    this.formularioRecuperar = this.formBuilder.group({
      email: ['', [
        Validators.required,
        Validators.email
      ]]
    });
    this.formulario = this.formBuilder.control({
      email: ['', [
        Validators.required,
        Validators.email
      ]]
    })
  }

  ngOnInit(): void {
    this.submitted = false;
    this.usernameEmail = false;
    this.usernameEmail = false;

  }
  receberEmail(email:string){
    console.log('email: ', email)
    this.formularioRecuperar.controls[this.FIEL_EMAIL].setValue(email);
    this.validaCampos(this.FIEL_EMAIL);
    this.storageService.setItemStorage('email', email);
  }
  recuperar(){
    this.submitted = true;
    console.log('recuperar');
  };
  private validacaoFormulario(){
    this.validaCampos(this.FIEL_EMAIL);
  }
  private validaCampos(campo:string):void{

    if(campo == '')
      return;
    if(campo == this.FIEL_EMAIL)
      this.emailRequerid = false;
    const erros = this.formularioRecuperar.get(campo)?.errors || {};
    const tipoErro = Object.keys(erros)[0];

    if(erros && tipoErro == 'required' && campo == this.FIEL_EMAIL)
      this.emailRequerid = true;
    if(erros && tipoErro == 'email' && campo == this.FIEL_EMAIL)
      this.usernameEmail = true;

  }
}
