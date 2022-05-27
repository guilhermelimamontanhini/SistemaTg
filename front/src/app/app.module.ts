import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { JwtModule } from '@auth0/angular-jwt';
import { ToastyModule } from 'ng2-toasty';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { AppComponent } from './app.component';
import { AppRoutingModule, routing } from './app.routing.module';
import { HttpModule } from '@angular/http';
import { AlistadosModule } from './alistados/alistados.module';
import { DispensadosModule } from './dispensados/dispensados.module';
import { MenuModule } from './menu/menu.module';
import { AtiradorModule } from './atirador/atirador.module';
import { DesligadosModule } from './desligados/desligados.module';
import { GuardasModule } from './guardas/guardas.module';
import { FattModule } from './fatt/fatt.module';
import { PelotoesModule } from './pelotoes/pelotoes.module';
import { RelatoriosModule } from './relatorios/relatorios.module';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        MenuModule,
        AtiradorModule,
        AlistadosModule,
        DispensadosModule,
        DesligadosModule,
        GuardasModule,
        FattModule,
        PelotoesModule,
        RelatoriosModule,
        BrowserModule,
        HttpModule,
        ToastyModule.forRoot(),
        AppRoutingModule,
        RouterModule,
        BrowserAnimationsModule,
        HttpClientModule,
        routing,
        JwtModule.forRoot({
            config: {
            }
        })
    ],
    providers: [
        ConfirmationService,
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
