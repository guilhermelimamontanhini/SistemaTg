import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { DataGuarda } from 'src/app/guardas/shared/model/dataGuarda.model';
import { DownloadService } from 'src/app/models/service/download.service';
import { RelatoriosService } from '../shared/service/relatorios.service';

@Component({
  selector: 'app-relatorios',
  templateUrl: './relatorios.component.html',
  styleUrls: ['./relatorios.component.css']
})
export class RelatoriosComponente implements OnInit {

  public spinnerConfirmarTodosAtiradores: boolean;
  public spinnerConfirmarPontos: boolean;
  public spinnerConfirmarAlistados: boolean;
  public spinnerConfirmarDespensados: boolean;
  public spinnerConfirmarDesligados: boolean;
  public spinnerConfirmarRefratarios: boolean;
  public spinnerConfirmarAtiraram: boolean;
  public spinnerConfirmarAlunos: boolean;
  public spinnerConfirmarMonitores: boolean;
  public spinnerConfirmarGuarda: boolean;
  public spinnerConfirmarMembros: boolean;
  public desabilitarBotoes: boolean;
  public dialogGuarda: boolean;
  public dialogPelotao: boolean;

  public dataEscrita: string;
  public dataGuarda: DataGuarda = new DataGuarda();
  public numeroPelotao: number;

  constructor(private toastyService: ToastyService, private relatoriosService: RelatoriosService, private downloadService: DownloadService) { }

  ngOnInit() {
  }

  public gerarRelatorioTodosAtiradores(): void {

    this.desabilitarBotoes = true;
    this.spinnerConfirmarTodosAtiradores = true;

    this.relatoriosService.gerarRelatorioTodosAtiradores().subscribe(
      (relatorio) => {
        this.downloadService.baixaRelatorio(relatorio, 'relatorio-todos-atiradores.pdf');
        this.desabilitarBotoes = false;
        this.spinnerConfirmarTodosAtiradores = false;
        this.toastyService.wait("Relatório sendo baixado");
      },
      erro => {
        this.toastyService.error("Não foi possível gerar");
        this.spinnerConfirmarTodosAtiradores = false;
        this.desabilitarBotoes = false;
      }
    )

  }

  public gerarRelatorioPontosAtirador(): void {

    this.desabilitarBotoes = true;
    this.spinnerConfirmarPontos = true;

    this.relatoriosService.gerarRelatorioPontosAtiradores().subscribe(
      (relatorio) => {
        this.downloadService.baixaRelatorio(relatorio, 'relatorio-pontos-atiradores.pdf');
        this.desabilitarBotoes = false;
        this.spinnerConfirmarPontos = false;
        this.toastyService.wait("Relatório sendo baixado");
      },
      erro => {
        this.toastyService.error("Não foi possível gerar");
        this.spinnerConfirmarPontos = false;
        this.desabilitarBotoes = false;
      }
    )

  }

  public gerarRelatorioAlistadosRefratarios(tipo: number): void {

    this.desabilitarBotoes = true;
    
    if(tipo === 0) {
      this.spinnerConfirmarRefratarios = true;
    } else {
      this.spinnerConfirmarAlistados = true;
    }

    this.relatoriosService.gerarRelatorioAlistadosRefratarios(tipo).subscribe(
      (relatorio) => {
        if (tipo === 0) {
          this.downloadService.baixaRelatorio(relatorio, 'relatorio-refratarios.pdf');
        } else {
          this.downloadService.baixaRelatorio(relatorio, 'relatorio-alistados.pdf');
        }
       
        this.desabilitarBotoes = false;
        this.spinnerConfirmarRefratarios = false;
        this.spinnerConfirmarAlistados = false;
        this.toastyService.wait("Relatório sendo baixado");
      },
      erro => {
        this.toastyService.error("Não foi possível gerar");
        this.spinnerConfirmarRefratarios = false;
        this.spinnerConfirmarAlistados = false;
        this.desabilitarBotoes = false;
      }
    )

  }

  public gerarRelatorioDespensados(): void {

    this.desabilitarBotoes = true;
    this.spinnerConfirmarDespensados = true;

    this.relatoriosService.gerarRelatorioDespensados().subscribe(
      (relatorio) => {
        this.downloadService.baixaRelatorio(relatorio, 'relatorio-despensados.pdf');
        this.desabilitarBotoes = false;
        this.spinnerConfirmarDespensados = false;
        this.toastyService.wait("Relatório sendo baixado");
      },
      erro => {
        this.toastyService.error("Não foi possível gerar");
        this.spinnerConfirmarDespensados = false;
        this.desabilitarBotoes = false;
      }
    )

  }

  public gerarRelatorioDesligados(): void {

    this.desabilitarBotoes = true;
    this.spinnerConfirmarDesligados = true;

    this.relatoriosService.gerarRelatorioPontosAtiradores().subscribe(
      (relatorio) => {
        this.downloadService.baixaRelatorio(relatorio, 'relatorio-desligados.pdf');
        this.desabilitarBotoes = false;
        this.spinnerConfirmarDesligados = false;
        this.toastyService.wait("Relatório sendo baixado");
      },
      erro => {
        this.toastyService.error("Não foi possível gerar");
        this.spinnerConfirmarDesligados = false;
        this.desabilitarBotoes = false;
      }
    )

  }

  public abrirDialogGuarda(): void {
    this.dialogGuarda = true;
  }

  public fecharDialogGuarda(): void {
    this.dialogGuarda = false;
    this.dataEscrita = "";
  }

 public gerarRelatorioGuarda(): void {

    this.desabilitarBotoes = true;
    this.spinnerConfirmarGuarda = true;

    this.dataGuarda.dataGuarda = this.dataEscrita;

    this.relatoriosService.gerarRelatorioGuardaDia(this.dataGuarda).subscribe(
      (relatorio) => {
        this.downloadService.baixaRelatorio(relatorio, 'relatorio-guarda.pdf');
        this.desabilitarBotoes = false;
        this.spinnerConfirmarGuarda = false;
        this.toastyService.wait("Relatório sendo baixado");
      },
      erro => {
        this.toastyService.error("Não foi possível gerar");
        this.spinnerConfirmarGuarda = false;
        this.desabilitarBotoes = false;
      }
    )

 }

  public abrirDialogPelotao(): void {
    this.dialogPelotao = true;
  }

  public fecharDialogPelotao(): void {
    this.dialogPelotao = false;
    this.numeroPelotao = 0;
  }

  public gerarRelatorioMembrosPelotao(): void {

    this.desabilitarBotoes = true;
    this.spinnerConfirmarMembros = true;

    this.dataGuarda.dataGuarda = this.dataEscrita;

    this.relatoriosService.gerarRelatorioMembrosPelotao(this.numeroPelotao).subscribe(
      (relatorio) => {
        this.downloadService.baixaRelatorio(relatorio, 'relatorio-membros-guarda.pdf');
        this.desabilitarBotoes = false;
        this.spinnerConfirmarMembros = false;
        this.toastyService.wait("Relatório sendo baixado");
      },
      erro => {
        this.toastyService.error("Não foi possível gerar");
        this.spinnerConfirmarMembros = false;
        this.desabilitarBotoes = false;
      }
    )

 }

 public gerarRelatorioAlunos(): void {

  this.desabilitarBotoes = true;
  this.spinnerConfirmarAlunos = true;

  this.relatoriosService.gerarRelatorioAlunosCurso().subscribe(
    (relatorio) => {
      this.downloadService.baixaRelatorio(relatorio, 'relatorio-alunos-curso.pdf');
      this.desabilitarBotoes = false;
      this.spinnerConfirmarAlunos = false;
      this.toastyService.wait("Relatório sendo baixado");
    },
    erro => {
      this.toastyService.error("Não foi possível gerar");
      this.spinnerConfirmarAlunos = false;
      this.desabilitarBotoes = false;
    }
  )

}

public gerarRelatorioMonitores(): void {

  this.desabilitarBotoes = true;
  this.spinnerConfirmarMonitores = true;

  this.relatoriosService.gerarRelatorioMonitores().subscribe(
    (relatorio) => {
      this.downloadService.baixaRelatorio(relatorio, 'relatorio-monitores.pdf');
      this.desabilitarBotoes = false;
      this.spinnerConfirmarMonitores = false;
      this.toastyService.wait("Relatório sendo baixado");
    },
    erro => {
      this.toastyService.error("Não foi possível gerar");
      this.spinnerConfirmarMonitores = false;
      this.desabilitarBotoes = false;
    }
  )

}

public gerarRelatorioAtiraram(): void {

  this.desabilitarBotoes = true;
  this.spinnerConfirmarAtiraram = true;

  this.relatoriosService.gerarRelatorioAtiraram().subscribe(
    (relatorio) => {
      this.downloadService.baixaRelatorio(relatorio, 'relatorio-atiraram.pdf');
      this.desabilitarBotoes = false;
      this.spinnerConfirmarAtiraram = false;
      this.toastyService.wait("Relatório sendo baixado");
    },
    erro => {
      this.toastyService.error("Não foi possível gerar");
      this.spinnerConfirmarAtiraram = false;
      this.desabilitarBotoes = false;
    }
  )

}

}
