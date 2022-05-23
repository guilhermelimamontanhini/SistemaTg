import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponente } from './menu-componente/menu.component';

@NgModule({
  declarations: [
    MenuComponente
  ],
  imports: [
    CommonModule
  ], exports: [
    MenuComponente
  ]
})
export class MenuModule { }
