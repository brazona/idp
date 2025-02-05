import {Component, EventEmitter, OnInit, Output} from '@angular/core';
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
import {
  RepeatNewPasswordFieldComponent
} from "../../components/fields/repeat_new_password/repeat-new-password.field.component";

@Component({
  selector: 'app-atualizacao',
  standalone: true,
  imports: [
    PasswordButtonComponent,
    FlexModule,
    NgIf,
    RouterLink,
    NewPasswordFieldComponent,
    RepeatNewPasswordFieldComponent
  ],
  templateUrl: './atualizacao.component.html',
  styleUrl: './atualizacao.component.scss'
})
export class AtualizacaoComponent implements OnInit{
  enabledButton = false;
  passwordNewRequerid:boolean = false;
  passwordNewRepeatRequerid:boolean = false;
  passwordNewEqual:boolean = false;
  passwordNewRepeatEquals:boolean = false;

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
    this.passwordNewEqual = false;
    this.passwordNewRepeatRequerid = false;
    this.passwordNewRepeatEquals = false;
    this.enabledButton = false;
  }
  receberNewPassword(new_password:string){
    // this.submitted = false;
    // this.passwordNewRequerid = true;
    // this.passwordNewEqual = true;
    // this.passwordNewRepeatRequerid = true;
    // this.passwordNewRepeatEquals = true;
    // this.enabledButton = false;
    if (!this.validateFields(this.FIEL_NEW_PASSWORD, new_password)){
      return;
    }
    // this.submitted = true;
    // this.passwordNewRequerid = false;
    // this.passwordNewEqual = false;
    // this.passwordNewRepeatRequerid = false;
    // this.passwordNewRepeatEquals = false;
    // this.enabledButton = true;
    // this.storageService.setItemStorage('recovery_new_password', this.cryptService.encrypt(new_password));
    //
    // this.formularioAtualizar.controls[this.FIEL_NEW_PASSWORD].setValue(new_password);

    this.storageService.setItemStorage('recovery_new_password', this.cryptService.encrypt(new_password));
  }
  receberRepeatNewPassword(repeat_new_password_value:string){
    // this.submitted = false;
    // this.passwordNewRequerid = true;
    // this.passwordNewEqual = true;
    // this.passwordNewRepeatRequerid = true;
    // this.passwordNewRepeatEquals = true;
    // this.enabledButton = false;
    if (!this.validateFields(this.FIEL_REPEAT_NEW_PASSWORD, repeat_new_password_value)){
      return;
    }
    // this.submitted = true;
    // this.passwordNewRequerid = false;
    // this.passwordNewEqual = false;
    // this.passwordNewRepeatRequerid = false;
    // this.passwordNewRepeatEquals = false;
    // this.enabledButton = true;
    this.storageService.setItemStorage('recovery_repeat_new_password', this.cryptService.encrypt(repeat_new_password_value));

  }
  atualizar(){
    this.submitted = true;
  };

  private validaCampos(campo:string):void{

    if(campo == '')
      return;
    if(campo == this.FIEL_NEW_PASSWORD)
      this.passwordNewRepeatRequerid = false;
    const erros = this.formularioAtualizar.get(campo)?.errors || {};
    const tipoErro = Object.keys(erros)[0];

    if(erros && tipoErro == 'required' && campo == this.FIEL_NEW_PASSWORD)
      this.passwordNewRepeatRequerid = true;
  }
  private validateFields(field: string, value: string): boolean{
    debugger
    this.submitted = false;
    this.passwordNewRequerid = true;
    this.passwordNewEqual = true;
    this.passwordNewRepeatRequerid = true;
    this.passwordNewRepeatEquals = true;
    this.enabledButton = false;

    if (!field)
      return false;
    if (field == this.FIEL_REPEAT_NEW_PASSWORD && !value){
      return false;
    }
    if (field == this.FIEL_REPEAT_NEW_PASSWORD && value){
      let passwordNew = this.storageService.getItemStorage('recovery_new_password');
      if (!passwordNew)
        return false;
      let passwordNewDecrypt = this.cryptService.decrypt(passwordNew);
      if (passwordNewDecrypt != value){
        return false;
      }
    }
    if (field == this.FIEL_NEW_PASSWORD && !value){
      return false;
    }
    this.submitted = true;
    this.passwordNewRequerid = false;
    this.passwordNewEqual = false;
    this.passwordNewRepeatRequerid = false;
    this.passwordNewRepeatEquals = false;
    this.enabledButton = true;
    return true;
  };
}
