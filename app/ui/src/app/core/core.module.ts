import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { provideHttpClient, withInterceptorsFromDi } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { coreGuard } from './guards/core.guard';
import { Interceptor } from './interceptors/interceptor';
import {LoadingInterceptor} from "./interceptors/loading.interceptor";
import {AuthGuard} from "./guards/auth.guard";
import {RecoveryGuard} from "./guards/recovery.guard";


@NgModule(
    {
        declarations: [],
        imports: [
            CommonModule,
            BrowserModule,
            BrowserAnimationsModule,
            RouterModule
         ],
        providers: [
            coreGuard,
            AuthGuard,
            RecoveryGuard,
            Interceptor,
            LoadingInterceptor,
            {
                provide: HTTP_INTERCEPTORS,
                useClass: Interceptor,
                multi: true,
            },
            provideHttpClient(withInterceptorsFromDi())
        ] })
export class CoreModule { }
