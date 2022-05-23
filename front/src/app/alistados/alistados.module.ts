import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlistadosComponente } from './alistados-componente/alistados.component';


@NgModule({
  declarations: [
    AlistadosComponente
  ],
  imports: [
    CommonModule
  ], exports: [
    AlistadosComponente
  ]
})
export class AlistadosModule { }
