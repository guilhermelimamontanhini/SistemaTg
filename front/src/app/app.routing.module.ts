import { ModuleWithProviders, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlistadosComponente } from './alistados/alistados-componente/alistados.component';
import { AtiradorComponente } from './atirador/atirador-componente/atirador.component';
import { DesligadosComponente } from './desligados/desligados-componente/desligados.component';
import { DispensadosComponente } from './dispensados/dispensados-componente/dispensados.component';
import { FattComponente } from './fatt/fatts-componente/fatt.component';
import { GuardasComponente } from './guardas/guardas-componente/guardas.component';
import { PelotoesComponente } from './pelotoes/pelotoes-componente/pelotoes.component';
import { RelatoriosComponente } from './relatorios/relatorios-componente/relatorios.component';

const routes: Routes = [
    {path: '', component: AtiradorComponente},
    {path: 'alistados', component: AlistadosComponente},
    {path: 'dispensados', component: DispensadosComponente},
    {path: 'desligados', component: DesligadosComponente},
    {path: 'guardas', component: GuardasComponente},
    {path: 'fatts', component: FattComponente},
    {path: 'pelotoes', component: PelotoesComponente},
    {path: 'relatorios', component: RelatoriosComponente}
  ];

  export const routing: ModuleWithProviders = RouterModule.forRoot(routes);

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })

export class AppRoutingModule { }
