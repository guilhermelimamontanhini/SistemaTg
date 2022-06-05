import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { AdicionarJustificativa } from '../modal/adicionarJustificativa.model';
import { FATT } from '../modal/fatt.model';

@Injectable({
  providedIn: 'root'
})
export class FattService {

  constructor( private http: HttpClient, private conexao: ConexaoService ) { }

  /**
   * 
   * @description Serviço para listar todos os FATTs
   * @return {Observable<FATT[]>}
   */
  public listarOsFatts(): Observable<FATT[]> {
    return this.http.get<FATT[]>(this.conexao.getServidor() + `/fatt`);
  }

  /**
   * 
   * @description Serviço para adicionar justificativa ao fatt
   * @param {number} id 
   * @param {AdicionarJustificativa} adicionarJustificativa 
   * @return {any}
   */
  public adicionarJustificativa(id: number, adicionarJustificativa: AdicionarJustificativa): any {
    return this.http.put(this.conexao.getServidor() + `/fatt/justificativa/` + id, adicionarJustificativa);
  }
  
  public aplicarFATT(id: number, justificativa: boolean): any {
    return this.http.delete(this.conexao.getServidor() + `/fatt/aplicarfatt/` + id + `/` + justificativa);
  }

}
