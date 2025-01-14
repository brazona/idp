import {Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Token} from '../interfaces/token/token'
import {GenericService} from './generic.service';
import {User} from '../interfaces/user/user';
import {NotificationService} from "./notification.service";
import {NotificationInterface} from "../interfaces/notification/notification.interface";
import {NotificationTypeEnum} from "../enuns/notificationType.enum";
import {NotificationMessageEnum} from "../enuns/notificationMessage.enum";
import {AuthorizationInterface} from "../interfaces/auth/authorization.interface";
import {LoadingService} from "./loading.service";
import {StorageService} from "./storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService extends GenericService{

  private authBase64: string = "";
  private isUserAuth: boolean = false;

  private isAuthorization: boolean | undefined = false;
  private readonly URL_API:string = 'http://localhost:7782/api'
  private readonly URL:string = 'http://localhost:7782/api/v1/auth/authentication'
  private readonly URL_AUTHORIZATION:string = 'http://localhost:7782/api/v1/auth/authorization'
  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
  constructor(router: Router, private http: HttpClient, private notication: NotificationService
              , private loadingService: LoadingService, private storageService: StorageService) {
    super(router)
  }
  recovery(email: string): Observable <any>{
    this.loadingService.loadingOn();
    let user: User = {
      username: email,
      password: email
    }
    return new Observable((observer) => {
        this.http.post<Token>(this.URL_API+'/v1/auth/forgot',JSON.stringify(user),
          {
            headers: this.getHeaderBasic(),
            observe: 'response'
          }
        )
          .subscribe(
            res =>{

              this.notication.sendMessage({
                message: NotificationMessageEnum.recovery_success, type: NotificationTypeEnum.success});
              this.loadingService.loadingOff();
              this.storageService.setItemStorage('is_user_update', 'true');
              this.router.navigate(["/recuperacao/validacao"]);
              observer.next(true);
            },
            (err: HttpErrorResponse) =>{
              if (err.status == 404)
                this.notication.sendMessage({message: NotificationMessageEnum.recovery_error, type: NotificationTypeEnum.error})
              else
                this.notication.sendMessage({message: NotificationMessageEnum.recovery_error, type: NotificationTypeEnum.error})
              console.log(err);
              this.loadingService.loadingOff();
              observer.next(true);
            }
          );

      }

    )};
  authentication(user: User): Observable <any>{
    this.builderHeader64();
    this.loadingService.loadingOn();
    return new Observable((observer) => {
      this.http.post<Token>(this.URL,JSON.stringify(user),
        {
          headers: new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Authorization', `Basic ${this.authBase64}`),
          observe: 'response'
        }
      )
      .subscribe(
        res =>{
          this.notication.sendMessage({
            message: NotificationMessageEnum.auth_success, type: NotificationTypeEnum.success});
          this.setSession(res.body?.token);
          this.loadingService.loadingOff();
          this.router.navigate(["/home"]);
          observer.next(true);
        },
        (err: string) =>{
          this.notication.sendMessage({message: NotificationMessageEnum.auth_error, type: NotificationTypeEnum.error})
          console.log(err);
          this.loadingService.loadingOff();
          observer.next(true);
        }
      );

    }

  )};
  authorization(token: String): Observable<boolean> | boolean {
    this.isAuthorization = false;
    this.loadingService.loadingOn();
    return new Observable((observer) => {
        this.http.post<AuthorizationInterface>(this.URL_AUTHORIZATION,JSON.stringify(token),
          {
            headers: new HttpHeaders()
              .set('Content-Type', 'application/json')
              .set('Authorization', `Bearer ${token}`),
            observe: 'response'
          }
        )
          .subscribe(
            res =>{
              this.isAuthorization = res.body?.is_authorized;
              this.loadingService.loadingOff();
              observer.next(true);
            },
            (err: string) =>{
              this.notication.sendMessage({message: NotificationMessageEnum.authorization_error, type: NotificationTypeEnum.error})
              console.log(err);
              this.loadingService.loadingOff();
              this.router.navigate(["/autenticacao"]);
              observer.next(true);
            }
          );

      }

    )

  }
  private setSession(token: string | undefined) {
    if (typeof token === "string") {
      console.log('token: ', token);
      localStorage.setItem('token', token);
    }
  }
  logout() {
    localStorage.removeItem("token");
  }
  public isAthentication() : Observable<boolean> | boolean {
    if(this.verifyTokenStorage()) {
      return this.authorization(localStorage['token']);
    }
    return false;
  }
  private verifyTokenStorage(): Observable<boolean> | boolean {
    this.isUserAuth = false;
    if(localStorage['token'] != null) {
      this.isUserAuth =  true;
    }
    return this.isUserAuth;
  }
  private builderHeader64(){
    this.authBase64 = window.btoa(this.env.APP_API.BASIC_AUTH.USERNAME + ':' + this.env.APP_API.BASIC_AUTH.PASSWORD);
  }
  private getBasic(): string{
    return window.btoa(this.env.APP_API.BASIC_AUTH.USERNAME + ':' + this.env.APP_API.BASIC_AUTH.PASSWORD);
  }
  private getHeaderToken(): HttpHeaders {
    let token: string | null = this.storageService.getItemStorage('token');
    return new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`);
  }
  private getHeaderBasic(): HttpHeaders {
    return new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Basic ${this.getBasic()}`);
  }
}

