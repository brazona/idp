import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { UsernameComponent } from './fields/username/username.component';

@NgModule({
  declarations: [  
  ],
  imports: [
    BrowserModule,
    FormsModule,
    //app
    UsernameComponent
  ],
})
export class ComponentsModule { }
