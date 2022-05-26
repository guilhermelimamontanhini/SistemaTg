import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { AlistadosFORM } from '../shared/model/alistadoFORM.model';
import { Alistados } from '../shared/model/alistados.model';
import { AlistadoService } from '../shared/service/alistado.service';

@Component({
  selector: 'app-alistados-componente',
  templateUrl: './alistados.component.html',
  styleUrls: ['./alistados.component.css']
})
export class AlistadosComponente implements OnInit {

  public alistados: Alistados[] = [];
  public alistadoCadastradoOuNovo: AlistadosFORM = new AlistadosFORM;
  public colunasTabela: any[];

  public spinnerTabela: boolean;
  public spinnerConfirmar: boolean;
  public desabilitarBotoes: boolean;
  public cadastrarOuEditar: boolean;
  public dialogCadastrarEditarAlistado: boolean;

  public nomeAlistadoCadastrarEditar: String;
  public cpfAlistadoCadastrarEditar: String;
  public refratarioCadastrarEditar: String;

  constructor(
    private toasty: ToastyService,
    private alistadoService: AlistadoService
  ) {
    this.colunasTabela = [
      { field: 'cpf', header: 'CPF', style: 'coluna-cpf'},
      { field: 'nome', header: 'Nome', style: 'coluna-nome'},
      { field: 'refratario', header: 'Refratário', style: 'coluna-refratario'},
      { field: 'acao', header: 'Ação', style: 'coluna-acao'}
    ];
   }

  ngOnInit(): void {
    this.listarTodosOsAlistados();
  }
  
  getEventValue($event:any) :string {
    return $event.target.value;
  } 

  public listarTodosOsAlistados(): void {
    this.spinnerTabela = true;
    this.alistadoService.listarTodosAlistados().subscribe(
      (alistados: Alistados[]) => {
          this.alistados = alistados;
          this.spinnerTabela = false;
      }, (erro) => {
        this.spinnerTabela = false;
        this.toasty.error('Erro de conexão');
      }
    )
  }

  public abrirDialogDeCadastrarAlistado(): void {
    this.dialogCadastrarEditarAlistado = true;
    this.desabilitarBotoes = true;
    this.cadastrarOuEditar = true;
  }

  public abrirDialogDeEditarAlistado(alistado: Alistados): void {
    this.nomeAlistadoCadastrarEditar = alistado.nome;
    this.cpfAlistadoCadastrarEditar = alistado.cpf;
    this.refratarioCadastrarEditar = alistado.refratario;
    this.dialogCadastrarEditarAlistado = true;
    this.desabilitarBotoes = true;
    this.cadastrarOuEditar = false;
  }

  public fecharDialogDeCadastrarAlistado(): void {
    this.dialogCadastrarEditarAlistado = false;
    this.desabilitarBotoes = false;
    this.alistadoCadastradoOuNovo = new AlistadosFORM;
  }

  public editarOuCadastrarAlistado(): void {
      this.spinnerConfirmar = true;

      this.alistadoCadastradoOuNovo.nome = this.nomeAlistadoCadastrarEditar;
      this.alistadoCadastradoOuNovo.cpf = this.cpfAlistadoCadastrarEditar;
      this.alistadoCadastradoOuNovo.refratario = this.refratarioCadastrarEditar;

      console.log(this.alistadoCadastradoOuNovo);

     if(this.cadastrarOuEditar) {
      this.alistadoService.cadastrarAlistado(this.alistadoCadastradoOuNovo).subscribe(
        () => {
          this.spinnerConfirmar = false;
          this.listarTodosOsAlistados();
          this.fecharDialogDeCadastrarAlistado();
        }, 
        error => {
          this.spinnerConfirmar = false;
          this.fecharDialogDeCadastrarAlistado();
        }
      )
     }

  }

}
