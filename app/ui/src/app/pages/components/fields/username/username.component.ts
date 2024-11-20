import {ChangeDetectionStrategy, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-field-username',
  standalone: true,
  imports: [MatFormFieldModule, 
    MatInputModule, MatSelectModule, FlexLayoutModule, CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './username.component.html',
  styleUrl: './username.component.scss',
  exportAs: "ngModel"
})


export class UsernameComponent implements OnInit{
  @Output() public username = new EventEmitter();
  constructor(){}
  ngOnInit(): void {
    
  }
  atribuiUsername(event:any){
    this.username.emit(event.target.value);
  }
}
