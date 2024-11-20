import {ChangeDetectionStrategy, Component, ElementRef, signal} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { CommonModule, DOCUMENT } from '@angular/common';
import { EMPTY } from 'rxjs';

@Component({
  selector: 'app-field-password',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, FlexLayoutModule, MatIconModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './password.component.html',
  styleUrl: './password.component.scss'
})
export class PasswordComponent {
  passVisibility:boolean = false;
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
}
