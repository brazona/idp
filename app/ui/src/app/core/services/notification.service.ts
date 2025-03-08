import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {NotificationInterface} from "../interfaces/notification/notification.interface";
import {ToastrService} from "ngx-toastr";
import {NotificationTypeEnum} from "../enuns/notificationType.enum";
import {NotificationPositionEnum} from "../enuns/notificationPosition.enum";

@Injectable({
  providedIn: 'root'
})
export class NotificationService{
  private notificationSubject: Subject<NotificationInterface> = new Subject<NotificationInterface>();
  positionClass: NotificationPositionEnum = NotificationPositionEnum.bottomRight;
  public sendMessage(message: NotificationInterface): void{
    this.notificationSubject.next(message);
  }
  constructor(private toastService: ToastrService) {
    this.notificationSubject.subscribe(message =>{
      switch (message.type){
        case NotificationTypeEnum.success:
          this.toastService.success(message.message, 'Sucesso');
          break;
        case NotificationTypeEnum.warning:
          this.toastService.warning(message.message, 'Atenção');
          break;
        case NotificationTypeEnum.error:
          this.toastService.error(message.message, 'Erro');
          break;
        default:
          this.toastService.info(message.message, 'Informação');
          break;
      }
    }, err => {
      console.log('Erro no serviço de Notificação')
    })
  }
}
