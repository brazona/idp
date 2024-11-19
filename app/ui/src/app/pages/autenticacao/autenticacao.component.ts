import {ChangeDetectionStrategy, Component, signal} from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {FormControl, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms'

import { PageComponent } from '../components/background/page/page.component';
import { FormComponent } from '../components/background/form/form.component';
import { UsernameComponent } from '../components/fields/username/username.component';
import { PasswordComponent } from '../components/fields/password/password.component';
import { AccessComponent } from '../components/buttons/access/access.component';

@Component({
  selector: 'app-autenticacao',
  standalone: true,
  imports: [RouterLink, RouterOutlet, MatButtonToggleModule, 
    FlexLayoutModule, MatFormFieldModule, MatIconModule, MatInputModule, 
    MatButtonModule, MatDividerModule, FormsModule, ReactiveFormsModule, 
    PageComponent, FormComponent, UsernameComponent, PasswordComponent, AccessComponent ],

  templateUrl: './autenticacao.component.html',
  styleUrl: './autenticacao.component.scss'
})
export class AutenticacaoComponent {

  logar(){
    console.log("logando....")
  }
}
