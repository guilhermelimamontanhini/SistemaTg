import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FattComponente } from './fatts-componente/fatt.component';

@NgModule({
  declarations: [
    FattComponente
  ],
  imports: [
    CommonModule
  ], 
  exports: [
    FattComponente
  ]

})
export class FattModule { }
