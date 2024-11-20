import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule } from '@angular/forms';

import { AutenticacaoComponent } from './autenticacao/autenticacao.component';
import { RecuperacaoComponent } from './recuperacao/recuperacao.component';
import { ComponentsModule } from './components/components.module';

@NgModule({
  declarations: [  
  ],
  imports: [
    FlexLayoutModule,
    FormsModule,
    //app
    ComponentsModule,
    AutenticacaoComponent,
    RecuperacaoComponent    
  ],
})
export class PagesModule { }
