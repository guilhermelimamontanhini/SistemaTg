import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Alistados } from '../shared/model/alistados.model';
import { AlistadoService } from '../shared/service/alistado.service';

@Component({
  selector: 'app-alistados-componente',
  templateUrl: './alistados.component.html',
  styleUrls: ['./alistados.component.css']
})
export class AlistadosComponente implements OnInit {

  public alistados: Alistados[] = [];
  public colunasTabela: any[];

  public spinnerTabela: boolean = false;

  constructor(
    private toastr: ToastrService,
    private alistadoService: AlistadoService
  ) {
    this.colunasTabela = [
      { field: 'cpf', header: 'CPF', style: 'coluna-cpf'},
      { field: 'nome', header: 'Nome', style: 'coluna-nome'},
      { field: 'refratario', header: 'Refratário', style: 'coluna-refratario'},
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
          console.log(this.alistados);
          this.spinnerTabela = false;
          // this.toastr.success('Sucesso!', 'Alistados carregados');
      }, (erro) => {
        this.spinnerTabela = false;
        this.toastr.error('Erro', 'Erro de conexão!');
      }
    )
  }

}
