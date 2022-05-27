import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PelotoesComponente } from './pelotoes-componente/pelotoes.component';

@NgModule({
  declarations: [
    PelotoesComponente
  ],
  imports: [
    CommonModule
  ],
  exports: [
    PelotoesComponente
  ]
})
export class PelotoesModule { }
