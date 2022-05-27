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
  public colunasTabela: any[];

  public spinnerTabela: boolean;

  constructor(
    private toasty: ToastyService,
    private dispensadosService: DispensadosService
    ) {
    this.colunasTabela = [
      { field: 'cpf', header: 'CPF', style: 'coluna-cpf'},
      { field: 'nome', header: 'Nome', style: 'coluna-nome'},
      // { field: 'motivo', header: 'Motivo', style: 'coluna-motivo'},
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
      }, (error) => {
        this.spinnerTabela = false;
        this.toasty.error('Erro de conexão');
      } 
    )
  }

}
