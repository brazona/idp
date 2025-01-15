import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {StorageService} from "../../../../core/services/storage.service";
import {RecuperacaoButtomEnum} from "../../../../core/enuns/recuperacao.buttom.enum";

@Component({
  selector: 'app-btn-code',
  standalone: true,
  imports: [],
  templateUrl: './code.component.html',
  styleUrl: './code.component.scss'
})
export class ButtonCodeComponent implements OnInit{
  codigo:string
  @Output() public onValidar = new EventEmitter();
  constructor(private storageService: StorageService){}
  ngOnInit(): void {}

  validar(){
    this.storageService.setItemStorage('button_type', RecuperacaoButtomEnum.validate);
    console.log('ButtonCodeComponent');

  }
  receberCodigo(codigo:string) {
    this.codigo = codigo;
  }
}
