import {Component, OnInit} from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {FormControl, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms'
import { FormBuilder, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { PageComponent } from '../components/background/page/page.component';
import { FormComponent } from '../components/background/form/form.component';
import { UsernameComponent } from '../components/fields/username/username.component';
import { PasswordComponent } from '../components/fields/password/password.component';
import { AccessComponent } from '../components/buttons/access/access.component';

interface User {
  username:string,
  password:string
}
@Component({
  selector: 'app-autenticacao',
  standalone: true,
  imports: [RouterLink, RouterOutlet, MatButtonToggleModule, 
    FlexLayoutModule, MatFormFieldModule, MatIconModule, MatInputModule, 
    MatButtonModule, MatDividerModule, FormsModule, ReactiveFormsModule, 
    PageComponent, FormComponent, UsernameComponent, PasswordComponent, AccessComponent, CommonModule ],

  templateUrl: './autenticacao.component.html',
  styleUrl: './autenticacao.component.scss'
})

export class AutenticacaoComponent implements OnInit{
  username:string;
  usernameRequerid:boolean = false;
  usernameEmail:boolean = false;
  passwordRequerid:boolean = false;
  formularioAutenticacao: FormGroup;
  formulario: FormControl;
  submitted:boolean;
  FIEL_PASSWORD:string = 'password';
  FIEL_USERNAME:string = 'username';

  constructor(private formBuilder: FormBuilder) {

    // **************************************************
    // Abaixo utilizamos o formBuilder para construir
    // vários FormControls que fazem parte do formulário.
    // Cada FormControl valida um input do formulário
    // **************************************************
    this.formularioAutenticacao = this.formBuilder.group({
      username: ['', [
        Validators.required,
        Validators.email
      ]],
      password: ['', [
        Validators.required
      ]]
    });
    this.formulario = this.formBuilder.control({
      username: ['', [
        Validators.required,
        Validators.email
    ]]
    })
  }
  ngOnInit(): void {
    this.submitted = false;
    this.usernameRequerid = false;
    this.usernameEmail = false;
    this.passwordRequerid = false;
  }
  logar(){
    
    this.submitted = true;
    this.usernameRequerid = false;
    this.usernameEmail = false;
    this.passwordRequerid = false;

    this.validacaoFormulario();

    if (!this.formularioAutenticacao.valid) {
      console.log("Formulário inválido");
      alert("Invalido")
      return;
    }
    console.log("Formulário válido", this.formularioAutenticacao.value);
  }
  receberUsername(username:string){
    this.formularioAutenticacao.controls[this.FIEL_USERNAME].setValue(username);
    this.validaCampos(this.FIEL_USERNAME);
  }
  receberPassword(pass:string){
    this.formularioAutenticacao.controls[this.FIEL_PASSWORD].setValue(pass);   
    this.validaCampos(this.FIEL_PASSWORD);
  }
  private validacaoFormulario(){
    this.validaCampos(this.FIEL_USERNAME);
    this.validaCampos(this.FIEL_PASSWORD);
  }
  private validaCampos(campo:string):void{
    
    if(campo == '')
      return;
    if(campo == this.FIEL_PASSWORD)
      this.passwordRequerid = false;
    if(campo == this.FIEL_USERNAME)
      this.usernameRequerid = false;
      this.usernameEmail = false;
    
    const erros = this.formularioAutenticacao.get(campo)?.errors || {};
    const tipoErro = Object.keys(erros)[0]; 
    debugger
    if(erros && tipoErro == 'required' && campo == this.FIEL_USERNAME)
      this.usernameRequerid = true;
    if(erros && tipoErro == 'email' && campo == this.FIEL_USERNAME)
      this.usernameEmail = true;
    if (erros && tipoErro == 'required' && campo == this.FIEL_PASSWORD)
       this.passwordRequerid = true;
  }
}
