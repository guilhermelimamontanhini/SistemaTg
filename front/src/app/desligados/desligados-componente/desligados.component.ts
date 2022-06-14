import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { Desligados } from '../shared/model/desligados.model';
import { DesligadosService } from '../shared/service/desligados.service';

@Component({
  selector: 'app-desligados-componente',
  templateUrl: './desligados.component.html',
  styleUrls: ['./desligados.component.css']
})
export class DesligadosComponente implements OnInit {

  public desligados: Desligados[] = [];
  public desligadoDeletado: Desligados = new Desligados();
  public colunasTabela: any[];

  public motivo: String;

  public spinnerTabela: boolean;
  public desabilitarBotoes: boolean;
  public dialogMotivo: boolean;
  public dialogDeletar: boolean;

  constructor(
    private toasty: ToastyService,
    private desligadosService: DesligadosService
  ) { 
    this.colunasTabela = [
      { field: 'cpf', header: 'CPF', style: 'coluna-cpf'},
      { field: 'nome', header: 'Nome', style: 'coluna-nome'},
      { field: 'acao', header: 'Ação', style: 'coluna-acao'}
    ];
  }

  ngOnInit(): void {
    this.listarTodosDesligados();
  }

  /**
   * 
   * @description Método para listar todos os desligados
   * @return {void}
   */
  public listarTodosDesligados(): void {
    this.spinnerTabela = true;
   this.desligadosService.listarTodosDesligados().subscribe(
      (desligadosRetornados: Desligados[]) => {
        this.desligados = desligadosRetornados;
        this.spinnerTabela = false;
      },
      (erro) => {
        this.spinnerTabela = false;
        if (erro.status == 404) {
          this.toasty.warning('Não existem desligados cadastrados!');
        } else {
          this.toasty.error('Erro de conexão');
        }
      } 
    )
  }

  public abrirDialogMotivo(motivoDispensamento: String): void {
    this.dialogMotivo = true;
    this.motivo = motivoDispensamento;
  }

  public fecharDialogMotivo(): void {
    this.dialogMotivo = false;
    this.motivo = '';
  }

  public abrirDialogDeletar(desligadoQueSeraDeletado: Desligados) {
    this.desligadoDeletado = desligadoQueSeraDeletado;
    this.dialogDeletar = true;
  }

  public fecharDialogDeletar() {
    this.desligadoDeletado = new Desligados();
    this.dialogDeletar = false;
  }

  public deletarDesligado(id: Number): void {
    this.desabilitarBotoes = true;
    this.toasty.clearAll();
    this.desligadosService.deletarDesligado(this.desligadoDeletado.idDesligado).subscribe(
      () => {

      },
      (erro) => {
        this.desabilitarBotoes = false;
        if (erro.status === 200) {
          this.listarTodosDesligados();
          this.toasty.success("Desligado deletado com sucesso");
        }
        if(erro.status == 404) {
          this.toasty.warning("Desligado não encontrado");
        } else if(erro.status == 500) {
          this.toasty.error("Erro de conexão");
        }
        this.fecharDialogDeletar();
      }
    )

  }

}
