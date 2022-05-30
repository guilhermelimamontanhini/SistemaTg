import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { Atirador } from '../shared/model/atirador.model';
import { AtiradorService } from '../shared/service/atirador.service';

@Component({
  selector: 'app-atirador-componente',
  templateUrl: './atirador.component.html',
  styleUrls: ['./atirador.component.css']
})
export class AtiradorComponente implements OnInit {

  public atiradores: Atirador[] = [];
  public colunasTabela: any[];

  public spinnerTabela: boolean;

  constructor(
    private toasty: ToastyService,
    private atiradorService: AtiradorService
  ) { 
    this.colunasTabela = [
      { field: 'ra',header: 'RA', style: 'coluna-ra'},
      { field: 'cpf', header: 'CPF', style: 'coluna-cpf'},
      { field: 'nome', header: 'Nome', style: 'coluna-nome'},
      { field: 'nomeGuerra', header: 'Nome de Guerra', style: 'coluna-guerra'},
      { field: 'ocupacao', header: 'Ocupação', style: 'coluna-ocupacao'},
      { field: 'acao', header: 'Ação', style: 'coluna-acao'}
    ];
  }

  ngOnInit(): void {
    this.listarTodosOsAtiradores();
  }

  public listarTodosOsAtiradores(): void {
    this.atiradores = [];
    console.log(this.atiradores);
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

}
