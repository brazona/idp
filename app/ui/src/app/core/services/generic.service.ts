import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GenericService {
  protected env = environment;
  constructor(
    public router: Router
  ) {
  };
  protected getApiURL(): string {
    return 'http://' + this.env.APP_API.HOST + ':' + this.env.APP_API.PORT + '/api';
  }
}

