import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import moment from "moment";

import { Auth } from '../interfaces/auth/auth';
import {Token } from '../interfaces/token/token'
import { GenericService } from './generic.service';
import { User } from '../interfaces/user/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends GenericService{

  private authBase64: string = "";
  private isUserAuth: boolean = false;
  private readonly URL:string = 'http://localhost:7782/api/v1/auth/authentication'
  private body: User = {
    grant_type:"",
    username:"",
    password:""
  };
  private iToken: Token = {
    access_token:"",
    expires_in:0
  };
  constructor(router: Router, private http: HttpClient) {
    super(router)
  }

  login(user: User): Observable <any>{
    return new Observable((observer) => {
      this.builderHeader64();
      this.http.post<Token>(
        this.env.APP_API.URL+'/v1/auth/authentication', 
        JSON.stringify(user),
        {
          headers: new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Authorization', `Basic ${this.authBase64}`),
          observe: 'response'
        }
      )
      .subscribe(
        res =>{
          this.router.navigate(['/home']);
          observer.next(true);
        },
        (err: string) =>{
          console.log(err);
          observer.next(true);
        }
      );
      
    }

  )}
  private setSession(token: Token) {
    const expiresAt = moment().add(token.expires_in,'second');
    localStorage.setItem('token', token.access_token);
    localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()) );
  }
  logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("expires_at");
  }
  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  public isLoggedOut() {
    return !this.isLoggedIn();
  }

 public getExpiration() {
    return moment(localStorage.getItem("expires_at"));
  }
  public isAthentication(){
    return this.verifyTokenStorage();
  }
  private verifyTokenStorage():boolean{
    if(localStorage['token'] != null) {
      this.isUserAuth =  true;
    }
    return this.isUserAuth;
  }
  private builderBody(auth:Auth){
    this.body.grant_type = "this.env.APP_API.GRANT"
    this.body.username = auth.login;
    this.body.password = auth.password;
  }
  private builderHeader64(){
    this.authBase64 = window.btoa("this.env.APP_API.AUTH_USER" + ':' + "this.env.APP_API.AUTH_PASS");
  }
 
  
}

