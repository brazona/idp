import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanLoad, Route } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable()

export class coreGuard implements CanActivate, CanLoad {

  isValid:boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean>|Promise<boolean>|boolean {
    return this.verificarAcesso();
  }

  private verificarAcesso(): Observable<boolean> | boolean {
    return this.authService.isAthentication();
  }

  canLoad(route: Route): Observable<boolean>|Promise<boolean>|boolean {
    return this.verificarAcesso();
  }

}
