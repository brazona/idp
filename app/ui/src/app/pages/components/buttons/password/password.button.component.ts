import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {StorageService} from "../../../../core/services/storage.service";
import {RecuperacaoButtomEnum} from "../../../../core/enuns/recuperacao.buttom.enum";

@Component({
  selector: 'app-btn-new-password',
  standalone: true,
  imports: [],
  templateUrl: './password.button.component.html',
  styleUrl: './password.button.component.scss'
})
export class PasswordButtonComponent implements OnInit{

  @Output() public onAtualizar = new EventEmitter();
  constructor(private storageService: StorageService){}
  ngOnInit(): void {}

  atualizar(){
    this.storageService.setItemStorage('button_type', RecuperacaoButtomEnum.update);

  }
}
