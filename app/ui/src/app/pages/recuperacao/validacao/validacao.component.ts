import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FieldEmailComponent} from "../../components/fields/email/field.email.component";
import {FlexModule} from "@angular/flex-layout";
import {ForgotComponent} from "../../components/buttons/forgot/forgot.component";
import {NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../core/services/auth.service";
import {StorageService} from "../../../core/services/storage.service";
import {ButtonCodeComponent} from "../../components/buttons/code/code.component";
import {FieldCodeComponent} from "../../components/fields/code/field.code.component";

@Component({
  selector: 'app-validacao',
  standalone: true,
  imports: [
    FieldCodeComponent,
    FlexModule,
    NgIf,
    RouterLink,
    ButtonCodeComponent
  ],
  templateUrl: './validacao.component.html',
  styleUrl: './validacao.component.scss'
})
export class ValidacaoComponent implements OnInit{

  codigoRequerid:boolean = false;


  formularioValidar: FormGroup;
  formulario: FormControl;
  submitted:boolean;
  FIEL_EMAIL:string = 'codigo';

  @Output() public onRecuperar = new EventEmitter();

  constructor(private formBuilder: FormBuilder, private service: AuthService, private storageService: StorageService) {

    // **************************************************
    // Abaixo utilizamos o formBuilder para construir
    // vários FormControls que fazem parte do formulário.
    // Cada FormControl valida um input do formulário
    // **************************************************
    this.formularioValidar = this.formBuilder.group({
      codigo: ['', [
        Validators.required
      ]]
    });
  }

  ngOnInit(): void {
    this.submitted = false;
    this.codigoRequerid = false;
  }
  receberCodigo(codigo:string){

    this.formularioValidar.controls[this.FIEL_EMAIL].setValue(codigo);
    this.validaCampos(this.FIEL_EMAIL);
    this.storageService.setItemStorage('recuperacao_code', codigo);
  }
  validar(){
    console.log('ValidacaoComponent');
    this.submitted = true;
  };
  private validacaoFormulario(){
    this.validaCampos(this.FIEL_EMAIL);
  }
  private validaCampos(campo:string):void{

    if(campo == '')
      return;
    if(campo == this.FIEL_EMAIL)
      this.codigoRequerid = false;
    const erros = this.formularioValidar.get(campo)?.errors || {};
    const tipoErro = Object.keys(erros)[0];

    if(erros && tipoErro == 'required' && campo == this.FIEL_EMAIL)
      this.codigoRequerid = true;
  }
}
