import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatIconModule } from '@angular/material/icon'
import { MatInput } from '@angular/material/input'
import { MatToolbar } from '@angular/material/toolbar'
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { ToastrModule} from "ngx-toastr";
import * as moment from 'moment';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { PagesModule } from './pages/pages.module';
import { MaterialModule } from './core/material.module';



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    MatIconModule,
    MatInput,
    MatToolbar,
    MatSlideToggleModule,
    FormsModule,
    ToastrModule.forRoot(
      {
        timeOut: 5000, // 5 seconds
        closeButton: true,
        progressBar: true,
        countDuplicates: true,
        tapToDismiss: true,
        positionClass: 'bottom-right',
        progressAnimation: "decreasing"
      }
    ),
    //app
    MaterialModule,
    CoreModule,
    PagesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
