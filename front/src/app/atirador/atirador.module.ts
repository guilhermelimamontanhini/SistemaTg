import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AtiradorComponente } from './atirador-componente/atirador.component';



@NgModule({
  declarations: [AtiradorComponente],
  imports: [
    CommonModule
  ],
  exports: [
    AtiradorComponente
  ]
})
export class AtiradorModule { }
