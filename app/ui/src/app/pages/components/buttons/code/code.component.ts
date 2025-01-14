import { Component, EventEmitter, OnInit, Output } from '@angular/core';

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
  constructor(){}
  ngOnInit(): void {}
  validar(){
    console.log('recuperar forgot');
  }
  receberCodigo(codigo:string) {
    this.codigo = codigo;
  }
}
