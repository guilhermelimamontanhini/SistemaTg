import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GuardasComponente } from './guardas-componente/guardas.component';



@NgModule({
  declarations: [
    GuardasComponente
  ],
  imports: [
    CommonModule
  ], 
  exports: [
    GuardasComponente
  ]
})
export class GuardasModule { }
