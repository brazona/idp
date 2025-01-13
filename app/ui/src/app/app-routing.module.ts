import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { coreGuard } from './core/guards/core.guard';
import { HomeComponent } from './pages/home/home.component';
import { AutenticacaoComponent } from './pages/autenticacao/autenticacao.component';
import { RecuperacaoComponent } from './pages/recuperacao/recuperacao.component';



const routes: Routes = [

  { path: 'autenticacao', component: AutenticacaoComponent },  
  { path: 'recuperacao', component: RecuperacaoComponent },   
  { path: 'home', component: HomeComponent,
        canActivate: [
          coreGuard
        ] 
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '*', redirectTo: 'home', pathMatch: 'full' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
