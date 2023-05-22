import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { AdicionarJustificativa } from '../modal/adicionarJustificativa.model';
import { FATD } from '../modal/fatd.model';

@Injectable({
  providedIn: 'root'
})
export class FatdService {

  constructor( private http: HttpClient, private conexao: ConexaoService ) { }

  /**
   * 
   * @description Serviço para listar todos os FATD
   * @return {Observable<FATD[]>}
   */
  public listarOsFatds(): Observable<FATD[]> {
    return this.http.get<FATD[]>(this.conexao.getServidor() + `/fatd`);
  }

  /**
   * 
   * @description Serviço para adicionar justificativa ao fatd
   * @param {number} id 
   * @param {AdicionarJustificativa} adicionarJustificativa 
   * @return {any}
   */
  public adicionarJustificativa(id: number, adicionarJustificativa: AdicionarJustificativa): any {
    return this.http.put(this.conexao.getServidor() + `/fatd/justificativa/` + id, adicionarJustificativa);
  }
  
  public aplicarFATD(id: number, justificativa: boolean): any {
    return this.http.delete(this.conexao.getServidor() + `/fatd/aplicarfatd/` + id + `/` + justificativa);
  }

}
