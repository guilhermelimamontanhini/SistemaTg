import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GuardasComponente } from './guardas-componente/guardas.component';
import { TableModule } from 'primeng/table';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TooltipModule } from 'primeng/tooltip';
import { DialogModule } from 'primeng/dialog';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputMaskModule } from 'primeng/inputmask';
import { ToastyModule } from 'ng2-toasty';
import {CalendarModule} from 'primeng/calendar';
import {DropdownModule} from 'primeng/dropdown';

@NgModule({
  declarations: [
    GuardasComponente
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
    CalendarModule,
    DropdownModule
  ], 
  exports: [
    GuardasComponente
  ]
})
export class GuardasModule { }
