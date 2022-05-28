import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlistadosComponente } from './alistados-componente/alistados.component';
import {TableModule} from 'primeng/table';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TooltipModule } from 'primeng/tooltip';
import { DialogModule } from 'primeng/dialog';
import { FormsModule } from '@angular/forms';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import {RadioButtonModule} from 'primeng/radiobutton';
import {InputMaskModule} from 'primeng/inputmask';
import { ToastyModule } from 'ng2-toasty';


@NgModule({
  declarations: [
    AlistadosComponente
  ],
  imports: [
    CommonModule,
    TableModule,
    ProgressSpinnerModule,
    TooltipModule,
    DialogModule,
    FormsModule,
    CheckboxModule,
    InputTextModule,
    RadioButtonModule,
    InputMaskModule,
    ToastyModule.forRoot(),
  ], exports: [
    AlistadosComponente
  ]
})
export class AlistadosModule { }
