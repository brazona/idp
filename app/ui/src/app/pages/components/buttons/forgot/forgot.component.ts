import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {RecuperacaoButtomEnum} from "../../../../core/enuns/recuperacao.buttom.enum";
import {StorageService} from "../../../../core/services/storage.service";

@Component({
  selector: 'app-btn-forgot',
  standalone: true,
  imports: [],
  templateUrl: './forgot.component.html',
  styleUrl: './forgot.component.scss'
})
export class ForgotComponent implements OnInit{
  email:string
  @Output() public onRecuperar = new EventEmitter();
  constructor(private storageService: StorageService){}
  ngOnInit(): void {}
  recuperar(){
    console.log("recuperando....");
    this.storageService.setItemStorage('button_type', RecuperacaoButtomEnum.forgot);
  }
}
