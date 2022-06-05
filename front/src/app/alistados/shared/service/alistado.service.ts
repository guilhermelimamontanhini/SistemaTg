import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { Alistados } from '../model/alistados.model';
import { Observable } from 'rxjs';
import { AlistadosFORM } from '../model/alistadoFORM.model';
import { MensagemMotivos } from 'src/app/models/mensagemMotivos.model';

@Injectable({
  providedIn: 'root'
})
export class AlistadoService {

  constructor( private http: HttpClient, private conexao: ConexaoService ) { }

  /**
   * 
   * @description Serviço para retornar todos os alistados
   * @return {Observable<Alistados[]>}
   */
  public listarTodosAlistados():Observable<Alistados[]> {
    return this.http.get<Alistados[]>(this.conexao.getServidor() + `/alistado`);
  }

  /**
   * 
   * @description Serviço para cadastrar um novo alistado
   * @param {AlistadosFORM} alistado 
   * @return {Observable<AlistadosFORM>}
   */
  public cadastrarAlistado(alistado: AlistadosFORM): Observable<AlistadosFORM> {
    return this.http.post<AlistadosFORM>(this.conexao.getServidor() + `/alistado/cadastrar`, alistado);
  }

  /**
   * 
   * @description Serviço para atualizar o alistado selecionado
   * @param {Number} id 
   * @param {AlistadosFORM} alistado 
   * @return {Observable<AlistadosFORM>}
   */
  public atualizarAlistado(id: Number, alistado: AlistadosFORM): Observable<AlistadosFORM> {
    return this.http.put<AlistadosFORM>(this.conexao.getServidor() + `/alistado/atualizar/` + id, alistado);
  }

  /**
   * 
   * @description Serviço para dispensar um alistado e cadastra-lo na tabela de dispensados
   * @param {Number} id 
   * @param {MensagemMotivos} mensagemMotivos 
   * @return {any}
   */
  public dispensarAlistado(id: Number, mensagemMotivos: MensagemMotivos): any {
    return this.http.post(this.conexao.getServidor() + `/alistado/dispensar/` + id, mensagemMotivos);
  }

}
