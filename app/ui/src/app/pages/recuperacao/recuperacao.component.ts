import {Component, OnInit} from '@angular/core';
import {
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators,
  FormsModule,
  ReactiveFormsModule, FormBuilder,
} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormGroup } from '@angular/forms';
import {AccessComponent} from "../components/buttons/access/access.component";
import {FlexModule} from "@angular/flex-layout";
import {FormComponent} from "../components/background/form/form.component";
import {NgIf} from "@angular/common";
import {PageComponent} from "../components/background/page/page.component";
import {PasswordComponent} from "../components/fields/password/password.component";
import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {UsernameComponent} from "../components/fields/username/username.component";
import {AuthService} from "../../core/services/auth.service";
import {FieldEmailComponent} from "../components/fields/email/field.email.component";
import {ForgotComponent} from "../components/buttons/forgot/forgot.component";
import {StorageService} from "../../core/services/storage.service";

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-recuperacao',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule,
    ForgotComponent, FlexModule, FormComponent, NgIf, PageComponent,
    PasswordComponent, RouterLink, UsernameComponent, FieldEmailComponent, AccessComponent, RouterOutlet],
  templateUrl: './recuperacao.component.html',
  styleUrl: './recuperacao.component.scss'
})
export class RecuperacaoComponent implements OnInit{
  FIEL_EMAIL:string = 'email';
  emailFormat:boolean = false;
  emailRequerid:boolean = false;
  formularioRecuperacao: FormGroup;
  formulario: FormControl;
  submitted:boolean;

  constructor(private router: Router,private formBuilder: FormBuilder, private service: AuthService, private storageService: StorageService
              ) {

    // **************************************************
    // Abaixo utilizamos o formBuilder para construir
    // vários FormControls que fazem parte do formulário.
    // Cada FormControl valida um input do formulário
    // **************************************************
    this.formularioRecuperacao = this.formBuilder.group({
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
    this.emailFormat = false;
    this.emailRequerid = false;
  }
  recuperacao(){
    debugger
    let emailValue = this.storageService.getItemStorage('email');
    if (emailValue){
      this.receberOnEmail(emailValue);
    }
    this.submitted = true;
    this.emailFormat = false;
    this.emailRequerid = false;

    this.validacaoFormulario();

    if (!this.formularioRecuperacao.valid) {
      return;
    }
    var email = this.formularioRecuperacao.get(this.FIEL_EMAIL)?.value;
    this.service.recovery(email).subscribe(
    );
  }
  receberOnEmail(email:string){

    console.log('receberOnEmail', email);
    this.formularioRecuperacao.controls[this.FIEL_EMAIL].setValue(email);
    this.validaCampos(this.FIEL_EMAIL);
  }
private validacaoFormulario(){
    this.validaCampos(this.FIEL_EMAIL);
  }
private validaCampos(campo:string):void{
  debugger
  if(campo == '')
    return;
  if(campo == this.FIEL_EMAIL)
    this.emailRequerid = false;

  const erros = this.formularioRecuperacao.get(campo)?.errors || {};
  const tipoErro = Object.keys(erros)[0];

  if(erros && tipoErro == 'required' && campo == this.FIEL_EMAIL)
    this.emailRequerid = true;
  if(erros && tipoErro == 'email' && campo == this.FIEL_EMAIL)
    this.emailFormat = true;
}
}
