import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanLoad, Route } from '@angular/router';
import { AuthService } from '../services/auth.service';
import {StorageService} from "../services/storage.service";
import {NotificationMessageEnum} from "../enuns/notificationMessage.enum";
import {NotificationTypeEnum} from "../enuns/notificationType.enum";
import {NotificationService} from "../services/notification.service";
import {LoadingService} from "../services/loading.service";

@Injectable()

export class coreGuard implements CanActivate, CanLoad {

  isValid:boolean = false;
  private path:string;
  constructor(
    private authService: AuthService,
    private router: Router,
    private storageService: StorageService,
    private notification: NotificationService,
    private loadingService: LoadingService
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ): Observable<boolean>|Promise<boolean>|boolean {
    debugger
    this.verificaPath(route);
    const is_user_update = this.storageService.getItemStorage("is_user_update");
    var valido = this.verificarAcesso();
    if (!valido){
      this.router.navigate(["/autenticacao"]);
      return false;
    }
    if (is_user_update && is_user_update == "true" && this.path != "atualizacao"){
      this.router.navigate(["/recuperacao"]);
      this.loadingService.loadingOff();
      this.notification.sendMessage({message: NotificationMessageEnum.update_pass_info, type: NotificationTypeEnum.info});
      return false;
    }
    this.loadingService.loadingOff();
    return true;
  }

  canLoad(route: Route): Observable<boolean>|Promise<boolean>|boolean {
    return this.verificarAcesso();
  }
  private verificarAcesso(): Observable<boolean> | boolean {
    return this.authService.isAthentication();
  }
  private verificaPath(path: ActivatedRouteSnapshot):void{
    path.url.forEach(p => {
      this.path=p.path;
    })
  }
}
