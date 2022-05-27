import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { MensagemMotivos } from 'src/app/models/mensagemMotivos.model';
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
  public mensagemMotivoDispensamento: MensagemMotivos = new MensagemMotivos;
  public colunasTabela: any[];

  public spinnerTabela: boolean;
  public spinnerConfirmar: boolean;
  public desabilitarBotoes: boolean;
  public cadastrarOuEditar: boolean;
  public dialogCadastrarEditarAlistado: boolean;
  public dialogDispensarAlistado: boolean;

  public idAlistado: Number;
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

  public listarTodosOsAlistados(): void {
    this.alistados = [];
    console.log(this.alistados);
    this.spinnerTabela = true;
    this.alistadoService.listarTodosAlistados().subscribe(
      (alistadosRetornados: Alistados[]) => {
          this.alistados = alistadosRetornados;
          console.log(this.alistados);
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
    this.idAlistado = alistado.idAlistado;
    this.nomeAlistadoCadastrarEditar = alistado.nome;
    this.cpfAlistadoCadastrarEditar = alistado.cpf;
    this.refratarioCadastrarEditar = alistado.refratario;
    this.dialogCadastrarEditarAlistado = true;
    this.desabilitarBotoes = true;
    this.cadastrarOuEditar = false;
  }

  public fecharDialogDeCadastrarEditarAlistado(): void {
    this.dialogCadastrarEditarAlistado = false;
    this.desabilitarBotoes = false;
    this.alistadoCadastradoOuNovo = new AlistadosFORM;
    this.idAlistado = 0;
    this.nomeAlistadoCadastrarEditar = '';
    this.cpfAlistadoCadastrarEditar = '';
    this.refratarioCadastrarEditar = '';
    this.listarTodosOsAlistados();
  }

  public editarOuCadastrarAlistado(): void {
      this.spinnerConfirmar = true;

      this.alistadoCadastradoOuNovo.nome = this.nomeAlistadoCadastrarEditar;
      this.alistadoCadastradoOuNovo.cpf = this.cpfAlistadoCadastrarEditar;
      this.alistadoCadastradoOuNovo.refratario = this.refratarioCadastrarEditar;


     if(this.cadastrarOuEditar) {
      //  Cadastrar
      this.alistadoService.cadastrarAlistado(this.alistadoCadastradoOuNovo).subscribe(
        () => {
          this.spinnerConfirmar = false;
          this.fecharDialogDeCadastrarEditarAlistado();
        }, 
        error => {
          this.spinnerConfirmar = false;
          this.fecharDialogDeCadastrarEditarAlistado();
        }
      )
     } else {
      //  Editar
       this.alistadoService.atualizarAlistado(this.idAlistado, this.alistadoCadastradoOuNovo).subscribe(
         () => {
          this.spinnerConfirmar = false;
          this.fecharDialogDeCadastrarEditarAlistado();
         }, 
         error => {
          this.spinnerConfirmar = false;
          this.fecharDialogDeCadastrarEditarAlistado();
         }
       )
     }

  }

  public abrirDialogDispensarAlistado(alistado: Alistados) {
    this.dialogDispensarAlistado = true;
    this.idAlistado = alistado.idAlistado;
  }

  public fecharDialogDispensarAlistado() {
    this.dialogDispensarAlistado = false;
    this.mensagemMotivoDispensamento = new MensagemMotivos;
    this.listarTodosOsAlistados();
  }

  public dispensarAlistado() {
    this.spinnerConfirmar = true;

    this.alistadoService.dispensarAlistado(this.idAlistado, this.mensagemMotivoDispensamento).subscribe(
      () => {
        this.spinnerConfirmar = false;
        this.fecharDialogDispensarAlistado();
      }, 
      erro => {
        this.spinnerConfirmar = false;
        this.fecharDialogDispensarAlistado();
      }
    )
  }


}
