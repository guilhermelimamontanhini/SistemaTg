import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { Dispensados } from '../shared/model/dispensados.model';
import { DispensadosService } from '../shared/service/dispensados.service';

@Component({
  selector: 'app-dispensados-componente',
  templateUrl: './dispensados.component.html',
  styleUrls: ['./dispensados.component.css']
})
export class DispensadosComponente implements OnInit {

  public dispensados: Dispensados[] = [];
  public dispensadoDeletado: Dispensados = new Dispensados();
  public colunasTabela: any[];

  public motivo: String;

  public spinnerTabela: boolean;
  public desabilitarBotoes: boolean;
  public dialogMotivo: boolean;
  public dialogDeletar: boolean;

  constructor(
    private toasty: ToastyService,
    private dispensadosService: DispensadosService
    ) {
    this.colunasTabela = [
      { field: 'cpf', header: 'CPF', style: 'coluna-cpf'},
      { field: 'nome', header: 'Nome', style: 'coluna-nome'},
      { field: 'acao', header: 'Ação', style: 'coluna-acao'}
    ];
   }

  ngOnInit(): void {
    this.listarTodosDispensados();
  }

  public listarTodosDispensados(): void {
    this.spinnerTabela = true;
    this.dispensadosService.listarTodosDispensados().subscribe(
      (dispensadosRetornados: Dispensados[]) => {
        this.dispensados = dispensadosRetornados;
        this.spinnerTabela = false;
      }, (erro) => {
        this.spinnerTabela = false;
        if (erro.status == 404) {
          this.toasty.warning('Não existem dispensados cadastradas!');
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

  public abrirDialogDeletar(dispensadoQueSeraDeletado: Dispensados) {
    this.dispensadoDeletado = dispensadoQueSeraDeletado;
    this.dialogDeletar = true;
  }

  public fecharDialogDeletar() {
    this.dispensadoDeletado = new Dispensados();
    console.log("to aqui");
    this.dialogDeletar = false;
  }

  public deletarDispensado(id: Number): void {
    this.desabilitarBotoes = true;
    this.toasty.clearAll();
    this.dispensadosService.deletarDispensado(this.dispensadoDeletado.idDispensado).subscribe(
      () => {

      },
      (erro) => {
        this.desabilitarBotoes = false;
        if (erro.status === 200) {
          this.listarTodosDispensados();
          this.toasty.success("Dispensado deletado com sucesso");
        }
        if(erro.status == 404) {
          this.toasty.warning("Dispensado não encontrado");
        } else if(erro.status == 500) {
          this.toasty.error("Erro de conexão");
        }
        this.fecharDialogDeletar();
      }
    )

  }

}
