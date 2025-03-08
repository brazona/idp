import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {StorageService} from "../../../../core/services/storage.service";

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
  constructor(private storageService: StorageService){}
  ngOnInit(): void {}
  logar(){
    console.log("logando....");
  }
}
