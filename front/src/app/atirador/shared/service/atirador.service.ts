import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { Observable } from 'rxjs';
import { Atirador } from '../model/atirador.model';
import { MensagemMotivos } from 'src/app/models/mensagemMotivos.model';
import { AtualizarDadosAtirador } from '../model/atualizarDadosAtirador.model';
import { NovoAtirador } from '../model/novoAtirador.model';
import { OcorrenciaJustificativa } from '../model/ocorrenciaJustificativa.model';

@Injectable({
  providedIn: 'root'
})
export class AtiradorService {

  constructor(private http: HttpClient, private conexao: ConexaoService) { }

  /**
   * 
   * @description Serviço para listar todos os atiradores
   * @return {Observable<Atirador[]>}
   */
  public listarTodosAlistados():Observable<Atirador[]> {
    return this.http.get<Atirador[]>(this.conexao.getServidor() + `/atirador`);
  }

   /**
   * 
   * @description Serviço para dispensar um atirador e cadastra-lo na tabela de desligados
   * @param {number} id 
   * @param {MensagemMotivos} mensagemMotivos 
   * @return {any}
   */
    public desligarAtirador(id: number, mensagemMotivos: MensagemMotivos): any {
      return this.http.post(this.conexao.getServidor() + `/atirador/desligar/` + id, mensagemMotivos);
    }

    /**
     * 
     * @description Serviço para atualizar os dados do atirador
     * @param {number} ra 
     * @param {AtualizarDadosAtirador} dados 
     * @return {Observable<AtualizarDadosAtirador>}
     */
    public atualizarDadosAtirador(ra: number, dados: AtualizarDadosAtirador): Observable<AtualizarDadosAtirador> {
      return this.http.put<AtualizarDadosAtirador>(this.conexao.getServidor() + `/atirador/atualizar/` + ra, dados);
    }

    /**
     * 
     * @description Serviço para cadastrar um novo atirador
     * @param {number} id 
     * @param {NovoAtirador} novoAtirador 
     * @return {Observable<NovoAtirador>}
     */
    public cadastrarDadosAtirador(id: number, novoAtirador: NovoAtirador): Observable<NovoAtirador> {
      return this.http.post<NovoAtirador>(this.conexao.getServidor() + `/atirador/cadastrar/` + id, novoAtirador);
    }

    /**
     * 
     * @description Serviço para promover atirador para aluno
     * @param {number} ra 
     * @return {any}
     */
    public promoverAtiradorParaAluno(ra: number, promover: boolean): any {
      return this.http.put(this.conexao.getServidor() + `/atirador/ocupacao/` + ra + `/` + promover, '');
    }

    /**
     * 
     * @description Serviço para adicionar um fatd
     * @param {number} ra 
     * @param {OcorrenciaJustificativa} ocorrenciaJustificativa 
     * @return {Observable<OcorrenciaJustificativa>}
     */
    public adicionarFATD(ra: number, ocorrenciaJustificativa: OcorrenciaJustificativa): Observable<OcorrenciaJustificativa> {
      return this.http.put<OcorrenciaJustificativa>(this.conexao.getServidor() + `/atirador/fatd/` + ra, ocorrenciaJustificativa);
    }

}
