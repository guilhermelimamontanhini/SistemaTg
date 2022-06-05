import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { Desligados } from '../model/desligados.model';

@Injectable({
  providedIn: 'root'
})
export class DesligadosService {

  constructor( private http: HttpClient, private conexao: ConexaoService ) { }

  /**
   * 
   * @description Serviço para retornar todos os desligados
   * @return {Observable<Desligados[]>} 
   */
  public listarTodosDesligados():Observable<Desligados[]> {
    return this.http.get<Desligados[]>(this.conexao.getServidor() + `/desligado`);
  }

}
