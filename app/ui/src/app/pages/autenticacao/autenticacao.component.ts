import {ChangeDetectionStrategy, Component, OnInit, signal} from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
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
  username:string
  formularioAutenticacao: FormGroup;
  formulario: FormControl;

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
  ngOnInit(): void {}
  logar(){
    console.log("Formulário: ", this.formularioAutenticacao.value);
    if (!this.formularioAutenticacao.valid) {
      console.log("Formulário inválido");
      return;
    }
    console.log("Formulário válido", this.formularioAutenticacao.value);
  }
  receberUsername(username:string){
    this.formularioAutenticacao.controls['username'].setValue(username);   
  }
  receberPassword(pass:string){
    this.formularioAutenticacao.controls['password'].setValue(pass);   
  }
}
