import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { DialogModule } from 'primeng/dialog';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { AlistadosModule } from './alistados/alistados.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AlistadosModule,
    ProgressSpinnerModule,
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    TableModule,
    TooltipModule,
    DialogModule,
    FormsModule,
    CheckboxModule,
    InputTextModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
