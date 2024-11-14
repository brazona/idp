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

@Component({
  selector: 'app-page',
  standalone: true,
  imports: [RouterLink, RouterOutlet, MatButtonToggleModule, 
    FlexLayoutModule, MatFormFieldModule, MatIconModule, MatInputModule, 
    MatButtonModule, MatDividerModule, FormsModule, ReactiveFormsModule ],
  templateUrl: './page.component.html',
  styleUrl: './page.component.scss'
})
export class PageComponent {

}
