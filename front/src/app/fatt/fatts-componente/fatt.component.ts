import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { AdicionarJustificativa } from '../shared/modal/adicionarJustificativa.model';
import { FATT } from '../shared/modal/fatt.model';
import { FattService } from '../shared/service/fatt.service';

@Component({
  selector: 'app-fatt',
  templateUrl: './fatt.component.html',
  styleUrls: ['./fatt.component.css']
})
export class FattComponente implements OnInit {

  public fatts: FATT[] = [];
  public justificativaAdicionada: AdicionarJustificativa = new AdicionarJustificativa();
  public colunasTabela: any[];

  public idFatt: number;
  public nomeDeGuerra: string;
  public ocorrenciaJustificada: boolean;

  public spinnerTabela: boolean;
  public desabilitarBotoes: boolean;
  public dialogJustificativa: boolean;
  public dialogAplicar: boolean;

  constructor(
    private toasty: ToastyService, 
    private fattService: FattService
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
    this.listarTodosOsFatts();
  }

  /**
   * 
   * @description Método que retorna todos os fatts
   * @return {void}
   */
  public listarTodosOsFatts(): void {
    this.spinnerTabela = true;
    this.fattService.listarOsFatts().subscribe(
      (fattRetornados: FATT[]) => {
        this.fatts = fattRetornados;
        this.spinnerTabela = false;
      },
      (erro) => {
        this.spinnerTabela = false;
        if(erro.status == 404) {
          this.toasty.warning('Não existem fatts cadastrados!');
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
  public abrirDialogDeAdicionarJustificativa(idFatt: number) {
    this.idFatt = idFatt;
    this.dialogJustificativa = true;
  }

   /**
   * 
   * @description Método para fechar o Dialog de adicionar justificativa
   * @return {void}
   */
  public fecharDialogDeAdicionarJustificativa() {
    this.idFatt = 0;
    this.justificativaAdicionada = new AdicionarJustificativa();
    this.dialogJustificativa = false;
  }

   /**
   * 
   * @description Método para adicionar justificativa para o fatt
   * @return {void}
   */
  public adicionarJustificativaAoFATT(): void {
    this.desabilitarBotoes = true;
    this.toasty.clearAll();
    this.fattService.adicionarJustificativa(this.idFatt, this.justificativaAdicionada).subscribe(
      () => {
        this.desabilitarBotoes = false;
      },
      (erro) => {
        this.desabilitarBotoes = false;
        if (erro.status === 201) {
          this.listarTodosOsFatts();
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
   * @description Método para abrir o Dialog de aplicar FATT
   * @return {void}
   */
  public abrirDialogDeAplicarFATT(fatt: FATT): void {
    this.idFatt = fatt.idFatt;
    this.nomeDeGuerra = fatt.nomeGuerra;
    this.justificativaAdicionada.justificativa = fatt.justificativa;
    this.dialogAplicar = true;
  }

  /**
   * 
   * @description Método para fechar o Dialog de aplicar FATT
   * @return {void}
   */
  public fecharDialogDeAplicarFATT(): void {
    this.idFatt = 0;
    this.nomeDeGuerra = '';
    this.dialogAplicar = false;
  }

  /**
   * 
   * @description Método para aplicar FATT ao atirador
   * @return {void}
   */
  public aplicarFATT(): void {
    this.desabilitarBotoes = true;
    this.toasty.clearAll();
    this.fattService.aplicarFATT(this.idFatt, this.ocorrenciaJustificada).subscribe(
      () => {
        this.desabilitarBotoes = false;
      }, 
      (erro) => {
        this.desabilitarBotoes = false;
        if(erro.status == 200) {
          this.toasty.success("FATT aplicado com sucesso.");
        } else if (erro.status == 400) {
          this.toasty.warning("Erro ao aplicar fatt.")
        } else if (erro.status == 404) {
          this.toasty.error("FATTs não encontrado.");
        }
        this.fecharDialogDeAplicarFATT();
        this.listarTodosOsFatts();
      }
    )

  }

}
