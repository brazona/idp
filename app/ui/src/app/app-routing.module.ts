import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { coreGuard } from './core/guards/core.guard';
import { LoginComponent } from './pages/login/login.component';
const routes: Routes = [
  { path: 'login', component: LoginComponent  }, 
  { path: 'home', component: HomeComponent,
        canActivate: [coreGuard] 
    },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
