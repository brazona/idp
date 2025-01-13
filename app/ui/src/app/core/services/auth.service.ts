import {Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Token} from '../interfaces/token/token'
import {GenericService} from './generic.service';
import {User} from '../interfaces/user/user';
import {NotificationService} from "./notification.service";
import {NotificationInterface} from "../interfaces/notification/notification.interface";
import {NotificationTypeEnum} from "../enuns/notificationType.enum";
import {NotificationMessageEnum} from "../enuns/notificationMessage.enum";
import {AuthorizationInterface} from "../interfaces/auth/authorization.interface";

@Injectable({
  providedIn: 'root'
})
export class AuthService extends GenericService{

  private authBase64: string = "";
  private isUserAuth: boolean = false;
  private isAuthorization: boolean | undefined = false;
  private readonly URL:string = 'http://localhost:7782/api/v1/auth/authentication'
  private readonly URL_AUTHORIZATION:string = 'http://localhost:7782/api/v1/auth/authorization'
  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
  constructor(router: Router, private http: HttpClient, private notication: NotificationService) {
    super(router)
  }

  authentication(user: User): Observable <any>{
    console.log('authentication');
    this.builderHeader64();
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
          console.log('res: ', res);
          this.router.navigate(["/home"]);
          observer.next(true);
        },
        (err: string) =>{
          this.notication.sendMessage({message: NotificationMessageEnum.auth_error, type: NotificationTypeEnum.error})
          console.log(err);
          observer.next(true);
        }
      );

    }

  )}
  authorization(token: String): Observable<boolean> | boolean {
    console.log('authorization');
    this.isAuthorization = false;
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
              console.log('res: ', res);
              this.isAuthorization = res.body?.is_authorized;
              observer.next(true);
            },
            (err: string) =>{
              this.notication.sendMessage({message: NotificationMessageEnum.authorization_error, type: NotificationTypeEnum.error})
              console.log(err);
              this.router.navigate(["/autenticacao"]);
              observer.next(true);
            }
          );

      }

    )}
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
    if(localStorage['token'] != null) {
      this.isUserAuth =  true;
    }
    return this.isUserAuth;
  }
  private builderHeader64(){
    this.authBase64 = window.btoa(this.env.APP_API.BASIC_AUTH.USERNAME + ':' + this.env.APP_API.BASIC_AUTH.PASSWORD);
  }
}

