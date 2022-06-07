import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { SelectItem } from 'primeng/components/common/selectitem';
import { Atirador } from 'src/app/atirador/shared/model/atirador.model';
import { AtiradorService } from 'src/app/atirador/shared/service/atirador.service';
import { DataGuarda } from '../shared/model/dataGuarda.model';
import { Guardas } from '../shared/model/guardas.model';
import { NovaGuarda } from '../shared/model/novaGuarda.model';
import { GuardasService } from '../shared/service/guardas.service';

@Component({
  selector: 'app-guardas',
  templateUrl: './guardas.component.html',
  styleUrls: ['./guardas.component.css']
})
export class GuardasComponente implements OnInit {

  public colunasTabela: any[];
  public colunasTabelaAtirador: any[];
  public guardaDoDia: Guardas[] = [];
  public atiradores: Atirador[] = [];
  public dataGuardaDoDia: DataGuarda = new DataGuarda();
  public novaGuarda: NovaGuarda = new NovaGuarda();
  public listaAtiradores: SelectItem[] = [];

  public spinnerTabela: boolean;
  public spinnerConfirmar: boolean;
  public desabilitarBotoes: boolean;
  public dialogIntegrantes: boolean;
  public dialogCadastrarGuarda: boolean;

  public raAtirador: number;
  public dataEscrita: string;
  public tipoGuarda: string;

  constructor(
    private toasty: ToastyService,
    private guardaService: GuardasService,
    private atiradorService: AtiradorService
  ) {
   
   }

  ngOnInit() {
    this.colunasTabela = [
      { field: 'dataGuarda', header: 'Data', style: 'coluna-data'},
      { field: 'tipoGuarda', header: 'Tipo Guarda', style: 'coluna-tipo'},
      { field: 'acao', header: 'Ação', style: 'coluna-acao'}
    ];
    this.colunasTabelaAtirador = [
      { field: 'ra',header: 'RA', style: 'coluna-ra'},
      { field: 'nomeGuerra', header: 'Nome de Guerra', style: 'coluna-guerra'},
      { field: 'ocupacao', header: 'Ocupação', style: 'coluna-ocupacao'},
    ]
    this.toasty.warning('Nenhuma guarda selecionada');
  }

  /**
   * 
   * @description Método para listar a guarda selecionada
   */
  public listarGuardaDoDia(data: string): void {
    this.dataGuardaDoDia.dataGuarda = data;
    this.guardaDoDia = [];
    this.spinnerTabela = true;
    this.desabilitarBotoes = true;
    this.guardaService.listarGuarda(this.dataGuardaDoDia).subscribe(
      (guardaDia: Guardas) => {
        this.spinnerTabela = false;
        this.desabilitarBotoes = false;
        this.guardaDoDia.push(guardaDia);
        this.toasty.success('Guarda listado com sucesso');
      },
      (erro) => {
        this.spinnerTabela = false;
        this.desabilitarBotoes = false;
        this.toasty.error('Erro de conexão');
      }
    )

  }

  /**
   * 
   * @description Método para limpar filtro
   * @return {void}
   */
  public limparFiltro(): void {
    this.dataEscrita = '';
    this.dataGuardaDoDia.dataGuarda = '';
    this.guardaDoDia = [];
  }

  /**
   * 
   * @description Método para abrir dialog de integrantes da guarda
   * @return {void}
   */
  public abrirDialogDeIntegrantesGuarda(integrantesGuarda: Guardas): void {
    this.atiradores = integrantesGuarda.listaIntegrantes;
    this.dataEscrita = integrantesGuarda.dataGuarda;
    this.dialogIntegrantes = true;
  }

  /**
   * 
   * @description Método para fechar dialog de integrantes da guarda
   * @return {void}
   */
  public fecharDialogDeIntegrantesGuarda():void {
    this.atiradores = [];
    this.dialogIntegrantes = false;
  }

  /**
   * 
   * @description Método para abrir cadastrado da guarda
   * @return {void}
   */
  public abrirDialogDeCadastrarGuarda(): void {
    this.dialogCadastrarGuarda = true;
    this.listarTodosOsAtiradores();
  }

  /**
   * 
   * @description Método para listar os atiradores
   * @return {void}
   */
  public listarTodosOsAtiradores(): void {
    this.atiradores = [];
    this.spinnerTabela = true;
    this.atiradorService.listarTodosAlistados().subscribe(
      (atiradoresRetornados: Atirador[]) => {
        console.log(this.atiradores);
        atiradoresRetornados.forEach(lista => {
          this.listaAtiradores.push({
            value: lista.ra,
            label: lista.nome,
          });
        });
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
   * @description Método para fechar cadastrado da guarda
   * @return {void}
   */
  public fecharDialogCadastrarGuarda(): void {
    this.atiradores = [];
    this.dialogCadastrarGuarda = false;
    this.tipoGuarda = '';
    this.dataEscrita = '';
  }

  /**
   * 
   * @description Método para cadastrar guarda
   * @return {void}
   */
  public cadastrarGuarda(): void {

    this.spinnerConfirmar = true;
    this.novaGuarda.dataGuarda = this.dataEscrita;
    this.novaGuarda.tipoGuarda = this.tipoGuarda;

    this.guardaService.cadastrarIntegrante(this.raAtirador, this.novaGuarda).subscribe(
      () => {

      }, 
      (erro) => {
        this.spinnerConfirmar = false;
        if (erro.status === 201) {
          this.toasty.success('Integrante da guarda adicionado cadastrado com sucesso')
        } else if (erro.status === 400) {
          this.toasty.warning('Erro ao cadastrar nova guarda');
        } else {
          this.toasty.error('Erro de conexão');
        }
        this.fecharDialogCadastrarGuarda();
      }
    )

  }

}
