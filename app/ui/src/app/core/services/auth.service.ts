import {Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Token} from '../interfaces/token/token'
import {GenericService} from './generic.service';
import {User} from '../interfaces/user/user';
import {NotificationService} from "./notification.service";
import {NotificationTypeEnum} from "../enuns/notificationType.enum";
import {NotificationMessageEnum} from "../enuns/notificationMessage.enum";
import {AuthorizationInterface} from "../interfaces/auth/authorization.interface";
import {LoadingService} from "./loading.service";
import {StorageService} from "./storage.service";
import {ValidateInterface} from "../interfaces/auth/validate.interface";
import {UpdateInterface} from "../interfaces/auth/update.interface";
import {StorageEnum} from "../enuns/storage.enum";
import {ApiPathEnum} from "../enuns/api.path.enum";

@Injectable({
  providedIn: 'root'
})
export class AuthService extends GenericService{

  private authBase64: string = "";
  private isUserAuth: boolean = false;
  private list_storage: string[];
  private isAuthorization: boolean | undefined = false;
  private isAuthorizeSource = new Subject<boolean>();
  isAuthorize$ = this.isAuthorizeSource.asObservable();
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
  validateCode(validate: ValidateInterface): Observable <any>{
    this.loadingService.loadingOn();
    return new Observable((observer) => {
        this.http.post<Token>(this.URL_API+'/v1/auth/validate/code',JSON.stringify(validate),
          {
            headers: this.getHeaderBasic(),
            observe: 'response'
          }
        )
          .subscribe(
            res =>{

              this.notication.sendMessage({
                message: NotificationMessageEnum.validate_code_success, type: NotificationTypeEnum.success});
              this.loadingService.loadingOff();
              this.storageService.setItemStorage('is_user_update', 'false');
              const token = res.body?.token;
              if (token){
                this.storageService.setItemStorage('token',token)
              }

              this.router.navigate(["/recuperacao/atualizacao"]);
              observer.next(true);
            },
            (err: HttpErrorResponse) =>{
              if (err.status == 404)
                this.notication.sendMessage({message: NotificationMessageEnum.validate_code_error, type: NotificationTypeEnum.error})
              else
                this.notication.sendMessage({message: NotificationMessageEnum.validate_code_error, type: NotificationTypeEnum.error})
              console.log(err);
              this.loadingService.loadingOff();
              observer.next(true);
            }
          );

      }

    )};
  updatePassword(updatePassword: UpdateInterface): Observable <any>{
    this.loadingService.loadingOn();
    return new Observable((observer) => {
        this.http.post<Token>(this.URL_API+'/v1/auth/update/password',JSON.stringify(updatePassword),
          {
            headers: this.getHeaderToken(),
            observe: 'response'
          }
        )
          .subscribe(
            res =>{

              this.notication.sendMessage({
                message: NotificationMessageEnum.update_pass_success, type: NotificationTypeEnum.success});
              this.loadingService.loadingOff();
              this.storageService.setItemStorage('is_user_update', 'false');
              this.removeStorage();
              this.router.navigate(["/autenticacao"]);
              observer.next(true);
            },
            (err: HttpErrorResponse) =>{
              if (err.status == 404)
                this.notication.sendMessage({message: NotificationMessageEnum.update_pass_error, type: NotificationTypeEnum.error})
              else
                this.notication.sendMessage({message: NotificationMessageEnum.update_pass_error, type: NotificationTypeEnum.error})
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
          debugger;
          this.notication.sendMessage({
            message: NotificationMessageEnum.auth_success, type: NotificationTypeEnum.success});
          this.setSession(res.body?.token);
          this.storageService.setItemStorage('is_user_update', 'false');
          this.removeStorage();
          this.loadingService.loadingOff();
          this.router.navigate(["/home"]);
          observer.next(true);
        },
        (err: string ) =>{
          this.httpErrorResponse(JSON.parse(err), ApiPathEnum.authentication);
          observer.next(true);
        }
      );

    }

  )};
  authorization(token: String): Observable<boolean>{
    this.loadingService.loadingOn();
    debugger;
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
            if (this.isAuthorization)
              this.isAuthorizeSource.next(this.isAuthorization);
            else
              this.isAuthorizeSource.next(false);
            this.loadingService.loadingOff();
            observer.next(true);
          },
          (err: string) =>{
            this.notication.sendMessage({message: NotificationMessageEnum.authorization_error, type: NotificationTypeEnum.warning})
            console.log(err);
            this.loadingService.loadingOff();
            this.isAuthorizeSource.next(false);
            this.router.navigate(["/autenticacao"]);
            observer.next(false);
          }
        );
      }
    );
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
  private removeStorage(){
    this.storageService.clearItemStorage(StorageEnum.email);
    this.storageService.clearItemStorage(StorageEnum.recovery_code);
    this.storageService.clearItemStorage(StorageEnum.recovery_new_password);
    this.storageService.clearItemStorage(StorageEnum.recovery_repeat_new_password);
    this.storageService.clearItemStorage(StorageEnum.button_type);
  }
  private httpErrorResponse(responseError: HttpErrorResponse, path: string){
    if (responseError.status == 0 ){
      this.notication.sendMessage({message: NotificationMessageEnum.generic_error, type: NotificationTypeEnum.error})
    }
    else if (path == ApiPathEnum.authentication && responseError.status != 0 && responseError.status == 403 ){
    }
    this.loadingService.loadingOff();

  }
}

