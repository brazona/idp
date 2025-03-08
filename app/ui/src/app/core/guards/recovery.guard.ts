import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanLoad, Route } from '@angular/router';
import { AuthService } from '../services/auth.service';
import {StorageService} from "../services/storage.service";
import {NotificationService} from "../services/notification.service";
import {NotificationMessageEnum} from "../enuns/notificationMessage.enum";
import {NotificationTypeEnum} from "../enuns/notificationType.enum";

@Injectable()

export class RecoveryGuard implements CanActivate {

  private path:string;
  private isValidateCode = false;

  constructor(
    private authService: AuthService,
    private storageService: StorageService,
    private notification: NotificationService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot
  ): Observable<boolean>|Promise<boolean>|boolean {
    console.log('RecoveryGuard');
    return this.verificaRecuperacao(route);
  }

  private verificaRecuperacao(path: ActivatedRouteSnapshot): Observable<boolean> | boolean {
    this.consultaRota(path);
    const is_user_update = this.storageService.getItemStorage("is_user_update");
    debugger;
    if (is_user_update ){
      if (is_user_update == "true" && this.path != "atualizacao" && this.path != "validacao" ){
        this.isValidateCode = true;
        this.router.navigate(["/recuperacao"]);
        this.notification.sendMessage({message: NotificationMessageEnum.update_pass_info, type: NotificationTypeEnum.info});
      }
      if (is_user_update == "false" && this.path == "atualizacao"){
        this.router.navigate(["/autenticacao"]);
        this.notification.sendMessage({message: NotificationMessageEnum.forgot_pass_info, type: NotificationTypeEnum.info});
      }
    }
    return true;
  }
  private consultaRota(path: ActivatedRouteSnapshot):void{
    path.url.forEach(p => {
      this.path=p.path;
    })
  }
}
