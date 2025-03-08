import {ChangeDetectionStrategy, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-field-email',
  standalone: true,
  imports: [MatFormFieldModule,
    MatInputModule, MatSelectModule, FlexLayoutModule, CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './field.email.component.html',
  styleUrl: './field.email.component.scss',
  exportAs: "ngModel"
})


export class FieldEmailComponent implements OnInit{
  @Output() public email = new EventEmitter();
  constructor(){}
  ngOnInit(): void {

  }
  atribuiEmail(event:any){
    this.email.emit(event.target.value);
  }
}
