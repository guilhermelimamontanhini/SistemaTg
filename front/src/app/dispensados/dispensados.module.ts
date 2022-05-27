import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DispensadosComponente } from './dispensados-componente/dispensados.component';
import { TableModule } from 'primeng/table';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TooltipModule } from 'primeng/tooltip';
import { DialogModule } from 'primeng/dialog';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { CheckboxModule } from 'primeng/checkbox';
import { RadioButtonModule } from 'primeng/radiobutton';

@NgModule({
  declarations: [DispensadosComponente],
  imports: [
    CommonModule,
    TableModule,
    ProgressSpinnerModule,
    TooltipModule,
    DialogModule,
    FormsModule,
    CheckboxModule,
    InputTextModule,
    RadioButtonModule
  ], exports: [
    DispensadosComponente
  ]
})
export class DispensadosModule { }
