import { ModuleWithProviders, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlistadosComponente } from './alistados/alistados-componente/alistados.component';
import { AtiradorComponente } from './atirador/atirador-componente/atirador.component';
import { DispensadosComponente } from './dispensados/dispensados-componente/dispensados.component';

const routes: Routes = [
    {path: '', component: AtiradorComponente},
    {path: 'alistados', component: AlistadosComponente},
    {path: 'dispensados', component: DispensadosComponente}
  ];

  export const routing: ModuleWithProviders = RouterModule.forRoot(routes);

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })

export class AppRoutingModule { }
