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
import {RecuperacaoButtomEnum} from "../../core/enuns/recuperacao.buttom.enum";
import {NotificationMessageEnum} from "../../core/enuns/notificationMessage.enum";
import {NotificationTypeEnum} from "../../core/enuns/notificationType.enum";
import {HttpErrorResponse} from "@angular/common/http";
import {CryptService} from "../../core/services/crypt.service";
import {NotificationService} from "../../core/services/notification.service";

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
  FIEL_CODE:string = 'code';
  FIEL_NEW_PASSWORD:string = 'new_password';
  FIEL_REPEAT_NEW_PASSWORD:string = 'repeat_new_password';

  emailFormat:boolean = false;
  emailRequerid:boolean = false;
  formularioRecuperacao: FormGroup;
  formulario: FormControl;
  submitted:boolean;

  constructor(private router: Router,private formBuilder: FormBuilder, private service: AuthService,
              private storageService: StorageService,
              private cryptService: CryptService,
              private notification: NotificationService
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
      ]],
      code: ['', [
        Validators.required,
      ]],
      password: ['', [
        Validators.required,
      ]],
      new_password: ['', [
        Validators.required,
      ]],
      repeat_new_password: ['', [
        Validators.required,
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

    let button_type = this.storageService.getItemStorage('button_type');
    console.log('button: ', button_type);
    switch (button_type){
      case RecuperacaoButtomEnum.forgot:
        this.forgot();
        break;
      case RecuperacaoButtomEnum.validate:
        this.validate();
        break;
      case RecuperacaoButtomEnum.update:
        this.update();
        break;
      default:
        return;
    }

  }
  receberOnEmail(email:string){
    this.formularioRecuperacao.controls[this.FIEL_EMAIL].setValue(email);
    this.validaCampos(this.FIEL_EMAIL);
  }
  private forgot(){
    console.log('forgot');
    let email = this.storageService.getItemStorage('email');
    this.formularioRecuperacao.controls[this.FIEL_EMAIL].setValue(email);
    this.validaCampos(this.FIEL_EMAIL);
    if (email){
      this.service.recovery(email).subscribe(
        res => {
          this.storageService.setItemStorage('is_user_update', 'true');
        },
      );
    }

  }
  private validate(){
    let code = this.storageService.getItemStorage('recuperacao_code');
    let email = this.storageService.getItemStorage('email');
    this.formularioRecuperacao.controls[this.FIEL_CODE].setValue(code);

    this.validaCampos(this.FIEL_CODE);
    if (!email){
      this.router.navigate(["/recuperacao"]);
    }
    if (code && email){
      this.service.validateCode({ username: email, code: code}).subscribe(
        res =>{
          this.storageService.setItemStorage('is_user_update', 'true');
        },
      );
    }

  }
  private update(){
    console.log('update');
     let new_password = this.storageService.getItemStorage('recovery_new_password');
     let repeat_new_password = this.storageService.getItemStorage('recovery_repeat_new_password');
    let code = this.storageService.getItemStorage('recuperacao_code');
    let email = this.storageService.getItemStorage('email');

    this.formularioRecuperacao.controls[this.FIEL_NEW_PASSWORD].setValue(new_password);
    this.formularioRecuperacao.controls[this.FIEL_REPEAT_NEW_PASSWORD].setValue(repeat_new_password);
    this.formularioRecuperacao.controls[this.FIEL_CODE].setValue(code);
    this.formularioRecuperacao.controls[this.FIEL_EMAIL].setValue(email);
    this.validaCampos(this.FIEL_NEW_PASSWORD);
    this.validaCampos(this.FIEL_REPEAT_NEW_PASSWORD);
    this.validaCampos(this.FIEL_CODE);
    this.validaCampos(this.FIEL_EMAIL);

    if (new_password && repeat_new_password && code && email){
      let passValid = new_password === repeat_new_password;
      if (!passValid){
        this.notification.sendMessage({message: NotificationMessageEnum.update_repeat_pass_error, type: NotificationTypeEnum.error});
        return;
      }
      let new_password_descryp = this.cryptService.decrypt(new_password);
      let repeat_new_password_descrypt = this.cryptService.decrypt(repeat_new_password);
      this.service.updatePassword({
        username: email,
        password: code,
        passwordNew: new_password_descryp,
        passwordRepeat: repeat_new_password_descrypt
      }).subscribe(
        res =>{

        },
      );
    }
  }

  private validaCampos(campo:string):void{
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
