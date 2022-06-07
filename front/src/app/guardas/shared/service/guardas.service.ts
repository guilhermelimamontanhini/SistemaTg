import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { DataGuarda } from '../model/dataGuarda.model';
import { Guardas } from '../model/guardas.model';
import { NovaGuarda } from '../model/novaGuarda.model';

@Injectable({
  providedIn: 'root'
})
export class GuardasService {

  constructor( private http: HttpClient, private conexao: ConexaoService ) { }

  /**
   * 
   * @description Serviço para listar guarda
   * @param {DataGuarda} dataGuarda 
   * @return {Observable<Guardas>}
   */
  public listarGuarda(dataGuarda: DataGuarda):Observable<Guardas> {
    return this.http.post<Guardas>(this.conexao.getServidor() + `/guarda`, dataGuarda);
  }

  /**
   * 
   * @description Serviço para cadastrar novo integrante a guarda
   * @param {number} ra 
   * @param {NovaGuarda} novaGuarda 
   * @return {Observable<NovaGuarda>}
   */
  public cadastrarIntegrante(ra: number, novaGuarda: NovaGuarda):Observable<NovaGuarda> {
    return this.http.post<NovaGuarda>(this.conexao.getServidor() + `/guarda/cadastrar/` + ra, novaGuarda);
  }

}
