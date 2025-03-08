import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule } from '@angular/forms';

import { AutenticacaoComponent } from './autenticacao/autenticacao.component';
import { RecuperacaoComponent } from './recuperacao/recuperacao.component';
import { ComponentsModule } from './components/components.module';
import { AuthService } from '../core/services/auth.service';

@NgModule({
  declarations: [
  ],
  imports: [
    FlexLayoutModule,
    FormsModule,
    //app
    AutenticacaoComponent,
    RecuperacaoComponent
  ],
  providers: [
    AuthService
  ]
})
export class PagesModule { }
