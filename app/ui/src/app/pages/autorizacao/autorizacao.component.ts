import {Component, ContentChild, Input, TemplateRef} from '@angular/core';
import {BackgroundLoadingComponent} from "../components/background/loading/background.loading.component";
import {AsyncPipe, NgTemplateOutlet} from "@angular/common";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {Observable, tap} from "rxjs";
import {LoadingService} from "../../core/services/loading.service";
import {RouteConfigLoadEnd, RouteConfigLoadStart, Router} from "@angular/router";

@Component({
  selector: 'app-autorizacao',
  standalone: true,
  imports: [BackgroundLoadingComponent, AsyncPipe, MatProgressSpinner, NgTemplateOutlet, BackgroundLoadingComponent],
  templateUrl: './autorizacao.component.html',
  styleUrl: './autorizacao.component.scss'
})
export class AutorizacaoComponent {

  loading$: Observable<boolean>;

  @Input()
  detectRouteTransitions = false;

  @ContentChild("loading")
  customLoadingIndicator: TemplateRef<any> | null = null;

  constructor(
    private loadingService: LoadingService,
    private router: Router) {
    this.loading$ = this.loadingService.loading$;
  }

  ngOnInit() {
    if (this.detectRouteTransitions) {
      this.router.events
        .pipe(
          tap((event) => {
            if (event instanceof RouteConfigLoadStart) {
              this.loadingService.loadingOn();
            } else if (event instanceof RouteConfigLoadEnd) {
              this.loadingService.loadingOff();
            }
          })
        )
        .subscribe();
    }
  }
}
