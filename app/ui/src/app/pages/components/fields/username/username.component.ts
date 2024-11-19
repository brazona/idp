import {ChangeDetectionStrategy, Component} from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-username',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatSelectModule, FlexLayoutModule, CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './username.component.html',
  styleUrl: './username.component.scss'
})


export class UsernameComponent {
}
