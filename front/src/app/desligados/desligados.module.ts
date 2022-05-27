import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DesligadosComponente } from './desligados-componente/desligados.component';

@NgModule({
  declarations: [
    DesligadosComponente
  ],
  imports: [
    CommonModule
  ], 
  exports: [
    DesligadosComponente
  ]
})
export class DesligadosModule { }
