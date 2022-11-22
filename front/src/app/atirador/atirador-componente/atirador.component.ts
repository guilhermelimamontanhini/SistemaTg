import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { SelectItem } from 'primeng/components/common/selectitem';
import { Alistados } from 'src/app/alistados/shared/model/alistados.model';
import { AlistadoService } from 'src/app/alistados/shared/service/alistado.service';
import { MensagemMotivos } from 'src/app/models/mensagemMotivos.model';
import { Atirador } from '../shared/model/atirador.model';
import { AtualizarDadosAtirador } from '../shared/model/atualizarDadosAtirador.model';
import { NovoAtirador } from '../shared/model/novoAtirador.model';
import { OcorrenciaJustificativa } from '../shared/model/ocorrenciaJustificativa.model';
import { AtiradorService } from '../shared/service/atirador.service';

@Component({
  selector: 'app-atirador-componente',
  templateUrl: './atirador.component.html',
  styleUrls: ['./atirador.component.css']
})
export class AtiradorComponente implements OnInit {

  public atiradores: Atirador[] = [];
  public informacoesAtirador: Atirador = new Atirador();
  public nomeGuerraNovoAtirador: NovoAtirador = new NovoAtirador();
  public atualizarDados: AtualizarDadosAtirador = new AtualizarDadosAtirador();
  public ocorrenciaJustificativa: OcorrenciaJustificativa = new OcorrenciaJustificativa();
  public colunasTabela: any[];
  public destaquesDisponiveis: SelectItem[] = [];
  public listaAlistados: SelectItem[] = [];
  public mensagemMotivoDesligamento: MensagemMotivos = new MensagemMotivos;

  public spinnerTabela: boolean;
  public dialogDesligarAtirador: boolean;
  public dialogEditarAtirador: boolean;
  public dialogCadastrar: boolean;
  public dialogPromocao: boolean;
  public dialogInformacoes: boolean;
  public dialogFATT: boolean;
  public spinnerConfirmar: boolean;
  public desabilitarBotoes: boolean;

  public ganhouDestaque: boolean = false;
  public nomeAtirador: string;
  
  public ocupacao: string;
  public idAlistado: number;

  public raAtirador: number;

  constructor(
    private toasty: ToastyService,
    private atiradorService: AtiradorService,
    private alistadoService: AlistadoService
  ) { 
    this.colunasTabela = [
      { field: 'ra',header: 'RA', style: 'coluna-ra'},
      { field: 'cpf', header: 'CPF', style: 'coluna-cpf'},
      { field: 'nome', header: 'Nome', style: 'coluna-nome'},
      { field: 'nomeGuerra', header: 'Nome de Guerra', style: 'coluna-guerra'},
      { field: 'ocupacao', header: 'Ocupação', style: 'coluna-ocupacao'},
      { field: 'acao', header: 'Ação', style: 'coluna-acao'}
    ];

    this.destaquesDisponiveis = [
      {label: 'Aptidão Física', value: 'F'},
      {label: 'Camaradagem', value: 'C'},
      {label: 'Melhor Tiro', value: 'T'},
      {label: 'Melhor do Ano', value: 'A'}
    ]

  }

  ngOnInit(): void {
    this.listarTodosOsAtiradores();
  }

  /**
   * 
   * @description Método para listar todos os atiradores 
   * @return {void}
   */
  public listarTodosOsAtiradores(): void {
    this.atiradores = [];
    this.spinnerTabela = true;
    this.atiradorService.listarTodosAlistados().subscribe(
      (atiradoresRetornados: Atirador[]) => {
          this.atiradores = atiradoresRetornados;
          this.spinnerTabela = false;
      }, 
      (erro) => {
        this.spinnerTabela = false;
        if(erro.status == 404) {
          this.toasty.warning('Não existem atiradores cadastrados!');
        } else {
          this.toasty.error('Erro de conexão');
        }
      }
    )
  }

  /**
   * 
   * @description Método para abrir o dialog de desligar atirador
   * @param {Atirador} atirador 
   */
  public abrirDialogDesligarAtirador(atirador: Atirador): void {
    this.dialogDesligarAtirador = true;
    this.raAtirador = atirador.ra;
  }

  /**
   * 
   * @description Método para fechar o dialog de desligar atirador
   */
  public fecharDialogDesligarAtirador(): void {
    this.dialogDesligarAtirador = false;
    this.mensagemMotivoDesligamento = new MensagemMotivos;
    this.listarTodosOsAtiradores();
  }

  /**
   * 
   * @description Método para desligar atirador
   * @return {void} 
   */
  public desligarAtirador(): void {
    this.spinnerConfirmar = true;

    this.atiradorService.desligarAtirador(this.raAtirador, this.mensagemMotivoDesligamento).subscribe(
      () => {
      }, 
      erro => {
        this.spinnerConfirmar = false;
        if (erro.status == 200) {
          this.toasty.success('Atirador desligado com sucesso');
        } else if(erro.status == 400) {
          this.toasty.warning("Erro ao desligar atirador.");
        } else if (erro.status == 404) {
          this.toasty.warning("Atirador não existe");
        } else {
          this.toasty.error("Erro de conexão");
        }
        this.fecharDialogDesligarAtirador();
      }
    )
  }

  /**
   * 
   * @description Método para abrir dialog de editar atirador
   * @param {Atirador} atiradorSelecionado 
   * @return {void}
   */
  public abrirDialogEditarAtirador(atiradorSelecionado: Atirador): void {
    this.raAtirador = atiradorSelecionado.ra;
    this.nomeAtirador = atiradorSelecionado.nome;
    this.ocupacao = atiradorSelecionado.ocupacao;
    this.atualizarDados.nomeGuerra = atiradorSelecionado.nomeGuerra;
    this.atualizarDados.pontos = atiradorSelecionado.pontos;
    this.atualizarDados.quantidadeGuardaDiaSemana = atiradorSelecionado.quantidadeGuardaDiaSemana;
    this.atualizarDados.quantidadeGuardaFimSemana = atiradorSelecionado.quantidadeGuardaFimSemana;
    this.atualizarDados.concluiuTg = atiradorSelecionado.concluiuTg;
    this.atualizarDados.realizouTiro = atiradorSelecionado.realizouTiro;
    this.atualizarDados.destaque = atiradorSelecionado.destaque;
    this.atualizarDados.cursoCabo = atiradorSelecionado.cursoCabo;
    this.dialogEditarAtirador = true;
  }

  /**
   * 
   * @description Método para fechar dialog de editar atirador
   * @return {void}
   */
  public fecharDialogEditarAtirador(): void {
    this.atualizarDados = new AtualizarDadosAtirador();
    this.raAtirador = 0;
    this.nomeAtirador = '';
    this.ocupacao = '';
    this.dialogEditarAtirador = false;
  }

  /**
   * 
   * @description Método para editar os dados do Atiraodor
   * @returns {void}
   */
  public editarDadosAtirador(): void {

    this.spinnerConfirmar = true;

    this.atiradorService.atualizarDadosAtirador(this.raAtirador,this.atualizarDados).subscribe(
      () => {
        this.spinnerConfirmar = false;
      },
      (erro) => {
        this.spinnerConfirmar = false;
        if(erro.status === 201) {
          this.toasty.success("Atirador atualizado.");
        } else if (erro.status === 400) {
          this.toasty.error("Erro ao atualizar atirador.");
        }
        this.fecharDialogEditarAtirador();
        this.listarTodosOsAtiradores();
      }
    )

  }

  /**
   * 
   * @description Método para abrir dialog de cadastrar atirador
   * @return {void}
   */
  public abrirDialogCadastrarAtirador(): void {
    this.dialogCadastrar = true;
    this.listarTodosOsAlistados();
  }

  /**
   * 
   * @description Método para fechar dialog de cadastrar atirador
   * @return {void}
   */
  public fecharDialogCadastrarAtirador(): void {
    this.dialogCadastrar = false;
    this.listaAlistados = [];
  }

  /**
   * 
   * @description Método para listar todos os alistados
   * @return {void}
   */
  public listarTodosOsAlistados(): void {
    this.spinnerTabela = true;
    this.alistadoService.listarTodosAlistados().subscribe(
      (alistadosRetornados: Alistados[]) => {
          alistadosRetornados.forEach(lista => {
            this.listaAlistados.push(
              {label: lista.nome, value: lista.idAlistado}
            );
          });
          console.log(this.listaAlistados);
          this.spinnerTabela = false;
      }, (erro) => {
        this.spinnerTabela = false;
        if(erro.status == 404) {
          this.toasty.warning('Não existem alistados cadastrados!');
        } else {
          this.toasty.error('Erro de conexão');
        }
      }
    )
  }

  /**
   * 
   * @description Método para cadastrar um novo atirador
   * @return {void}
   */
  public cadastrarNovoAtirador(): void {

    this.spinnerConfirmar = true;
    
    this.atiradorService.cadastrarDadosAtirador(this.idAlistado, this.nomeGuerraNovoAtirador).subscribe(
      () => {
      },
      (erro) => {
        this.spinnerConfirmar = false;
        console.log(erro);
        if(erro.status === 201) {
          this.toasty.success('Atirador cadastrado com sucesso.');
        } else if(erro.status === 411) {
          this.toasty.warning('A existem mais de cem atiradores cadastrados');
        } else if(erro.status === 400) {
          this.toasty.warning('Erro ao cadastrar atirador.');
        } else if(erro.status === 404) {
          this.toasty.error('Alistado não existe');
        }
        this.fecharDialogCadastrarAtirador();
        this.listarTodosOsAtiradores();
      }
    )

  }

  /**
   * 
   * @description Método para abrir dialog de promocao
   * @return {void}
   */
  public abrirDialogPromocao(atiradorSelecionado: Atirador): void {
    this.dialogPromocao = true;
    this.raAtirador = atiradorSelecionado.ra;
    this.nomeAtirador = atiradorSelecionado.nomeGuerra;
    this.ocupacao = atiradorSelecionado.ocupacao;
    
  }

  /**
   * 
   * @description Método para fechar dialog de promocao
   * @return {void}
   */
  public fecharDialogPromocao(): void {
    this.dialogPromocao = false;
    this.raAtirador = 0;
    this.nomeAtirador = '';
    this.ocupacao = '';
  }

  /**
   * 
   * @description Método para promover atirador para o curso de cabo
   * @return {void}
   */
  public promoverAtiradorParaAluno(): void {
    this.spinnerConfirmar = true;
    this.atiradorService.promoverAtiradorParaAluno(this.raAtirador).subscribe(
      () => {},
      (erro) => {
        this.spinnerConfirmar = false;
        if(erro.status === 201) {
          this.toasty.success('Ocupaçao atualizada.');
        } else {
          this.toasty.error('Erro ao atualizar atirador.');
        }
        this.fecharDialogPromocao();
        this.listarTodosOsAtiradores();
      }
    )

  }

  public abrirDialogInformacoes(atiradorSelecionado: Atirador): void {
    this.informacoesAtirador = atiradorSelecionado;
    this.dialogInformacoes = true;
  }

  public fecharDialogInformacoes(): void {
    this.informacoesAtirador = new Atirador();
    this.dialogInformacoes = false;
  }

   /**
   * 
   * @description Método para abrir dialog de fatt
   * @return {void}
   */
  public abrirDialogFATT(atiradorSelecionado: Atirador): void {
    this.raAtirador = atiradorSelecionado.ra;
    this.nomeAtirador = atiradorSelecionado.nomeGuerra;
    this.dialogFATT = true;
  }   

   /**
   * 
   * @description Método para abrir dialog de fatt
   * @return {void}
   */
  public fecharDialogFATT(): void {
    this.raAtirador = 0;
    this.dialogFATT = false;
    this.ocorrenciaJustificativa = new OcorrenciaJustificativa();
  }

  /**
   * 
   * @description Método para aplicar FATT no atirador
   * @return {void}
   */
  public adicionarFATTAoAtirador(): void {
    this.spinnerConfirmar = true;
    this.atiradorService.adicionarFATT(this.raAtirador, this.ocorrenciaJustificativa).subscribe(
      () => {},
      (erro) => {
        this.spinnerConfirmar = false;
        if (erro.status === 201) {  
          this.toasty.success('Apuração de FATT concluida.');
        } else if (erro.status === 400) {
          this.toasty.warning('Erro ao apurar FATT.');
        } else if (erro.status === 404) {
          this.toasty.warning('Atirador não existe');
        } else {
          this.toasty.error('Erro de conexão');
        }
        this.listarTodosOsAtiradores();
        this.fecharDialogFATT();
      }
    )

  }

}
