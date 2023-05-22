import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { AdicionarJustificativa } from '../shared/modal/adicionarJustificativa.model';
import { FATD } from '../shared/modal/fatd.model';
import { FatdService } from '../shared/service/fatd.service';

@Component({
  selector: 'app-fatd',
  templateUrl: './fatd.component.html',
  styleUrls: ['./fatd.component.css']
})
export class FatdComponente implements OnInit {

  public fatds: FATD[] = [];
  public justificativaAdicionada: AdicionarJustificativa = new AdicionarJustificativa();
  public colunasTabela: any[];

  public idFatd: number;
  public nomeDeGuerra: string;
  public ocorrenciaJustificada: boolean;

  public spinnerTabela: boolean;
  public desabilitarBotoes: boolean;
  public dialogJustificativa: boolean;
  public dialogAplicar: boolean;

  constructor(
    private toasty: ToastyService, 
    private fatdService: FatdService
  ) { 
    this.colunasTabela = [
      { field: 'ra',header: 'RA', style: 'coluna-ra'},
      { field: 'nomeGuerra', header: 'Nome de Guerra', style: 'coluna-guerra'},
      { field: 'ocorrencia', header: 'Ocorrência', style: 'coluna-ocorrencia'},
      { field: 'justificativa', header: 'Justificativa', style: 'coluna-justificativa'},
      { field: 'acao', header: 'Ação', style: 'coluna-acao'}
    ];
  }

  ngOnInit( ) {
    this.listarTodosOsFatds();
  }

  /**
   * 
   * @description Método que retorna todos os fatds
   * @return {void}
   */
  public listarTodosOsFatds(): void {
    this.spinnerTabela = true;
    this.fatdService.listarOsFatds().subscribe(
      (fatdRetornados: FATD[]) => {
        this.fatds = fatdRetornados;
        this.spinnerTabela = false;
      },
      (erro) => {
        this.spinnerTabela = false;
        if(erro.status == 404) {
          this.toasty.warning('Não existem fatds cadastrados!');
        } else {
          this.toasty.error('Erro de conexão');
        }
      }
    )

  }

  /**
   * 
   * @description Método para abrir o Dialog de adicionar justificativa
   * @return {void}
   */
  public abrirDialogDeAdicionarJustificativa(idFatd: number) {
    this.idFatd = idFatd;
    this.dialogJustificativa = true;
  }

   /**
   * 
   * @description Método para fechar o Dialog de adicionar justificativa
   * @return {void}
   */
  public fecharDialogDeAdicionarJustificativa() {
    this.idFatd = 0;
    this.justificativaAdicionada = new AdicionarJustificativa();
    this.dialogJustificativa = false;
  }

   /**
   * 
   * @description Método para adicionar justificativa para o fatd
   * @return {void}
   */
  public adicionarJustificativaAoFATD(): void {
    this.desabilitarBotoes = true;
    this.toasty.clearAll();
    this.fatdService.adicionarJustificativa(this.idFatd, this.justificativaAdicionada).subscribe(
      () => {
        this.desabilitarBotoes = false;
      },
      (erro) => {
        this.desabilitarBotoes = false;
        if (erro.status === 201) {
          this.listarTodosOsFatds();
          this.toasty.success("Justificativa adicionado com sucesso");
        }
        if(erro.status == 404) {
          this.toasty.warning("Erro ao atualizar justificativa.");
        } else if(erro.status == 500) {
          this.toasty.error("Erro de conexão");
        }
        this.fecharDialogDeAdicionarJustificativa();
      }
    )

  }

   /**
   * 
   * @description Método para abrir o Dialog de aplicar FATD
   * @return {void}
   */
  public abrirDialogDeAplicarFATD(fatd: FATD): void {
    this.idFatd = fatd.idFatd;
    this.nomeDeGuerra = fatd.nomeGuerra;
    this.justificativaAdicionada.justificativa = fatd.justificativa;
    this.dialogAplicar = true;
  }

  /**
   * 
   * @description Método para fechar o Dialog de aplicar FATD
   * @return {void}
   */
  public fecharDialogDeAplicarFATD(): void {
    this.idFatd = 0;
    this.nomeDeGuerra = '';
    this.dialogAplicar = false;
  }

  /**
   * 
   * @description Método para aplicar FATD ao atirador
   * @return {void}
   */
  public aplicarFATD(): void {
    this.desabilitarBotoes = true;
    this.toasty.clearAll();
    this.fatdService.aplicarFATD(this.idFatd, this.ocorrenciaJustificada).subscribe(
      () => {
        this.desabilitarBotoes = false;
      }, 
      (erro) => {
        this.desabilitarBotoes = false;
        if(erro.status == 200) {
          this.toasty.success("FATD aplicado com sucesso.");
        } else if (erro.status == 400) {
          this.toasty.warning("Erro ao aplicar fatd.")
        } else if (erro.status == 404) {
          this.toasty.error("FATDs não encontrado.");
        }
        this.fecharDialogDeAplicarFATD();
        this.listarTodosOsFatds();
      }
    )

  }

}
