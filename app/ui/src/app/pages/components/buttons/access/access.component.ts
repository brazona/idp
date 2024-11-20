import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-btn-access',
  standalone: true,
  imports: [],
  templateUrl: './access.component.html',
  styleUrl: './access.component.scss'
})
export class AccessComponent implements OnInit{
  username:string
  @Output() public onLogar = new EventEmitter();
  constructor(){}
  ngOnInit(): void {}
  logar(){
    console.log("logando....")
  }
  receberUsername(username:string) {
    this.username = username;
  }
}
