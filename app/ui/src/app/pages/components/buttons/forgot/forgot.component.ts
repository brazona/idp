import { Component, EventEmitter, OnInit, Output } from '@angular/core';

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
  constructor(){}
  ngOnInit(): void {}
  recuperar(){
    console.log('recuperar forgot');
  }
  receberEmail(email:string) {
    this.email = email;
  }
}
