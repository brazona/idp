import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { UsernameComponent } from './fields/username/username.component';
import { AuthService } from 'src/app/core/services/auth.service';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MatProgressSpinnerModule,
    //app
    UsernameComponent
  ],
  providers:[AuthService]
})
export class ComponentsModule { }
