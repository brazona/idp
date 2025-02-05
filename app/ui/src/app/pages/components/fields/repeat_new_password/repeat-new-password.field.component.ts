import {ChangeDetectionStrategy, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-field-repeat-new-password',
  standalone: true,
  imports: [CommonModule,  MatFormFieldModule,
    MatInputModule, MatSelectModule, FlexLayoutModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './repeat-new-password.field.component.html',
  styleUrl: './repeat-new-password.field.component.scss'
})
export class RepeatNewPasswordFieldComponent implements OnInit{

  passVisibility:boolean = false;

  @Output() public repeat_new_password = new EventEmitter();
  constructor(){}
  ngOnInit(): void {}
  showPass(){
    this.passVisibility = !this.passVisibility;
    const password = document.querySelector(".field-repeat-new-password");
    if (password)
      this.setType(password);
  }
  private setType(field:Element){
    const type = field.getAttribute('type') === 'password' ? 'text' : 'password';
    field.setAttribute('type', type)
  }
  atribuiRepeatNewPassword(event:any){
    this.repeat_new_password.emit(event.target.value);
  }
}
