import {Component, EventEmitter, Output} from '@angular/core';
import * as crypto from 'crypto-js';
import {FlexModule} from "@angular/flex-layout";
import {NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../core/services/auth.service";
import {StorageService} from "../../../core/services/storage.service";
import {PasswordComponent} from "../../components/fields/password/password.component";
import {PasswordButtonComponent} from "../../components/buttons/password/password.button.component"
import {NewPasswordFieldComponent} from "../../components/fields/new_password/new-password.field.component";
import {CryptService} from "../../../core/services/crypt.service";

@Component({
  selector: 'app-atualizacao',
  standalone: true,
  imports: [
    PasswordButtonComponent,
    FlexModule,
    NgIf,
    RouterLink,
    PasswordComponent,
    NewPasswordFieldComponent
  ],
  templateUrl: './atualizacao.component.html',
  styleUrl: './atualizacao.component.scss'
})
export class AtualizacaoComponent {
  passwordRequerid:boolean = false;
  passwordNewRequerid:boolean = false;

  formularioAtualizar: FormGroup;
  formulario: FormControl;
  submitted:boolean;
  FIEL_NEW_PASSWORD:string = 'new_password';
  FIEL_REPEAT_NEW_PASSWORD:string = 'repeat_new_password';

  @Output() public onAtualizar = new EventEmitter();

  constructor(private formBuilder: FormBuilder, private service: AuthService,
              private storageService: StorageService, private cryptService: CryptService) {

    // **************************************************
    // Abaixo utilizamos o formBuilder para construir
    // vários FormControls que fazem parte do formulário.
    // Cada FormControl valida um input do formulário
    // **************************************************
    this.formularioAtualizar = this.formBuilder.group({
      new_password: ['', [
        Validators.required
      ]],
      repeat_new_password: ['', [
        Validators.required
      ]],
    });
  }

  ngOnInit(): void {
    this.submitted = false;
    this.passwordNewRequerid = false;
    this.passwordRequerid = false;
  }
  receberPassword(password:string){
    this.formularioAtualizar.controls[this.FIEL_NEW_PASSWORD].setValue(password);
    this.validaCampos(this.FIEL_REPEAT_NEW_PASSWORD);
    this.storageService.setItemStorage('recovery_repeat_new_password', this.cryptService.encrypt(password));
  }
  receberNewPassword(new_password:string){
    this.formularioAtualizar.controls[this.FIEL_NEW_PASSWORD].setValue(new_password);
    this.validaCampos(this.FIEL_REPEAT_NEW_PASSWORD);
    this.storageService.setItemStorage('recovery_new_password', this.cryptService.encrypt(new_password));
  }
  atualizar(){
    this.submitted = true;
    console.log('atualizar');
  };
  validar(){
    console.log('ValidacaoComponent');
    this.submitted = true;
  };

  private validaCampos(campo:string):void{

    if(campo == '')
      return;
    if(campo == this.FIEL_NEW_PASSWORD)
      this.passwordRequerid = false;
    const erros = this.formularioAtualizar.get(campo)?.errors || {};
    const tipoErro = Object.keys(erros)[0];

    if(erros && tipoErro == 'required' && campo == this.FIEL_NEW_PASSWORD)
      this.passwordRequerid = true;
  }
}
