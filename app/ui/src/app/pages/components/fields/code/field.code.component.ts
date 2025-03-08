import {ChangeDetectionStrategy, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-field-code',
  standalone: true,
  imports: [MatFormFieldModule,
    MatInputModule, MatSelectModule, FlexLayoutModule, CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './field.code.component.html',
  styleUrl: './field.code.component.scss',
  exportAs: "ngModel"
})


export class FieldCodeComponent implements OnInit{
  @Output() public code = new EventEmitter();
  constructor(){}
  ngOnInit(): void {

  }
  atribuiCodigo(event:any){
    this.code.emit(event.target.value);
  }
}
