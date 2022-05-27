import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RelatoriosComponente } from './relatorios-componente/relatorios.component';


@NgModule({
  declarations: [
    RelatoriosComponente
  ],
  imports: [
    CommonModule
  ],
  exports: [
    RelatoriosComponente
  ]
})
export class RelatoriosModule { }
