import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlistadosComponente } from './alistados/alistados-componente/alistados.component';

const routes: Routes = [
  {path: '', component: AlistadosComponente}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
