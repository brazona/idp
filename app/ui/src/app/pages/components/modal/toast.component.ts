import { Component } from '@angular/core';
import { ToastService } from '../../../core/services/toast.service';

@Component({
  selector: 'toast-root',
  template: `
    <button (click)="showToast()">Show Toast</button>
    <div *ngFor="let toast of toastService.toasts; let i = index">
      {{ toast }}
      <button (click)="removeToast(i)">X</button>
    </div>
  `,
  standalone: true
})
export class ToastComponent {

  constructor(public toastService: ToastService) {}

  showToast() {
    this.toastService.add('This is a toast message.');
  }

  removeToast(index: number) {
    this.toastService.remove(index);
  }
}
