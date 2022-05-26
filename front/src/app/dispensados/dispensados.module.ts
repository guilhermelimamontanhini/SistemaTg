import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DispensadosComponente } from './dispensados-componente/dispensados.component';

@NgModule({
  declarations: [DispensadosComponente],
  imports: [
    CommonModule
  ], exports: [
    DispensadosComponente
  ]
})
export class DispensadosModule { }
