import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { coreGuard } from './core/guards/core.guard';
import { HomeComponent } from './pages/home/home.component';
import { AutenticacaoComponent } from './pages/autenticacao/autenticacao.component';
import { RecuperacaoComponent } from './pages/recuperacao/recuperacao.component';
import {EmailComponent} from "./pages/recuperacao/email/email.component";
import {ValidacaoComponent} from "./pages/recuperacao/validacao/validacao.component";
import {AtualizacaoComponent} from "./pages/recuperacao/atualizacao/atualizacao.component";



const routes: Routes = [

  { path: 'autenticacao', component: AutenticacaoComponent },
  { path: 'home', component: HomeComponent,
        canActivate: [
          coreGuard
        ]
  },
  {
    path: 'recuperacao',
    component: RecuperacaoComponent,
    children: [
      { path: '', component: EmailComponent },
      { path: 'validacao', component: ValidacaoComponent },
      { path: 'atualizacao', component: AtualizacaoComponent },
    ],
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '*', redirectTo: 'home', pathMatch: 'full' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
