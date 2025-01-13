import {ChangeDetectionStrategy, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-field-password',
  standalone: true,
  imports: [CommonModule,  MatFormFieldModule, 
    MatInputModule, MatSelectModule, FlexLayoutModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './password.component.html',
  styleUrl: './password.component.scss'
})
export class PasswordComponent implements OnInit{

  passVisibility:boolean = false;
  
  @Output() public password = new EventEmitter();
  constructor(){}
  ngOnInit(): void {}
  showPass(){
    this.passVisibility = !this.passVisibility;
    const password = document.querySelector(".field-password");
    if (password)
      this.setType(password);
  }
  private setType(field:Element){
    const type = field.getAttribute('type') === 'password' ? 'text' : 'password'; 
    field.setAttribute('type', type)
  }
  atribuiPassword(event:any){
    this.password.emit(event.target.value);
  }
}
