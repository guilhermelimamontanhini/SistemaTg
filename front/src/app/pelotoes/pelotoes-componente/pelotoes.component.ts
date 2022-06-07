import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { Atirador } from 'src/app/atirador/shared/model/atirador.model';
import { NovoPelotao } from '../shared/model/novoPelotao.modal';
import { Pelotao } from '../shared/model/pelotao.modal';
import { PelotaoService } from '../shared/service/pelotao.service';

@Component({
  selector: 'app-pelotoes',
  templateUrl: './pelotoes.component.html',
  styleUrls: ['./pelotoes.component.css']
})
export class PelotoesComponente implements OnInit {

  public pelotoes: Pelotao[] = [];
  public atiradores: Atirador[] = [];
  public novoPelotao: NovoPelotao = new NovoPelotao();
  public colunasTabela: any[];
  public colunasTabelaAtirador: any[];

  public spinnerTabela: boolean;
  public spinnerConfirmar: boolean;
  public dialogCadastrar: boolean;
  public dialogIntegrantes: boolean;

  public nomePelotao: string;

  constructor(
    private toasty: ToastyService,
    private pelotaoService: PelotaoService
  ) { 
    this.colunasTabela = [
      { field: 'numero', header: 'Número', style: 'coluna-numero'},
      { field: 'nome', header: 'Nome', style: 'coluna-nome'},
      { field: 'acao', header: 'Ação', style: 'coluna-acao'}
    ];
    this.colunasTabelaAtirador = [
      { field: 'ra',header: 'RA', style: 'coluna-ra'},
      { field: 'cpf', header: 'CPF', style: 'coluna-cpf'},
      { field: 'nome', header: 'Nome', style: 'coluna-nome'},
      { field: 'nomeGuerra', header: 'Nome de Guerra', style: 'coluna-guerra'},
      { field: 'ocupacao', header: 'Ocupação', style: 'coluna-ocupacao'},
    ]
  }

  ngOnInit() {
    this.listarTodosOsPelotoes();
  }

  public listarTodosOsPelotoes() {
    this.spinnerTabela = true;
    this.pelotaoService.listarTodosPelotoes().subscribe(
      (pelotoesListados: Pelotao[]) => {
        this.pelotoes = pelotoesListados;
        this.spinnerTabela = false;
      }, 
      (erro) => {
        this.spinnerTabela = false;
        if (erro.status === 404) {
          this.toasty.warning('Não existem pelotoes cadastrados!');
        } else {
          this.toasty.error('Erro de conexão');
        }
        
      }
    )

  }

  public abrirDialogCadastrarPelotao(): void {
    this.dialogCadastrar = true;
  }

  public fecharDialogCadastrarPelotao(): void {
    this.dialogCadastrar = false;
    this.novoPelotao = new NovoPelotao();
  }

  public cadastrarNovoPelotao(): void {
    this.spinnerConfirmar = true;
    this.pelotaoService.cadastrarPelotao(this.novoPelotao).subscribe(
      () => {},
      (erro) => {
        this.spinnerConfirmar = false;
        if(erro.status === 201) {
          if(this.pelotoes.length <= 2) {
            this.toasty.success('Pelotão cadastrado com sucesso.');
          } else {
            this.toasty.success('Atiradores cadastrados nos pelotões concluido com sucesso.');
          }
        } else if (erro.status === 422) {
          this.toasty.warning('Já existe o maximo de pelotões cadastrados');
        } else if (erro.status === 400) {
          this.toasty.error('Erro ao cadastrar alistado.');
        } else {
          this.toasty.error('Erro de conexão');
        }
        this.listarTodosOsPelotoes();
        this.fecharDialogCadastrarPelotao();
      }
    )
  }

  public abrirDialogDeIntegrantesPelotao(pelotaoSelecionado: Pelotao): void {
    this.atiradores = pelotaoSelecionado.atiradores;
    this.nomePelotao = pelotaoSelecionado.nome;
    this.dialogIntegrantes = true;
  }

  public fecharDialogDeIntegrantesPelotao():void {
    this.atiradores = [];
    this.dialogIntegrantes = false;
  }

}
