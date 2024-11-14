import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AutenticacaoComponent } from './autenticacao/autenticacao.component';
import { RecuperacaoComponent } from './recuperacao/recuperacao.component';

@NgModule({
  declarations: [  
  ],
  imports: [
    FlexLayoutModule,
    //app
    AutenticacaoComponent,
    RecuperacaoComponent    
  ]
})
export class PagesModule { }
