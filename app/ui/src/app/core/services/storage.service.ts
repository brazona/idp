import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";


@Injectable({
  providedIn: "root",
})
export class StorageService {

  getItemStorage(key: string): string | null {
    return localStorage.getItem(key);
  };
  setItemStorage(key: string, value: string): void {
    localStorage.setItem(key, value);
  }
  clearItemStorage(key: string): void {
    localStorage.removeItem(key);
  }
}
