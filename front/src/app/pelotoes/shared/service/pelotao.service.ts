import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { NovoPelotao } from '../model/novoPelotao.modal';
import { Pelotao } from '../model/pelotao.modal';

@Injectable({
  providedIn: 'root'
})
export class PelotaoService {

  constructor( private http: HttpClient, private conexao: ConexaoService ) { }

   /**
   * 
   * @description Serviço para retornar todos os pelotões
   * @return {Observable<Pelotao[]>}
   */
    public listarTodosPelotoes():Observable<Pelotao[]> {
      return this.http.get<Pelotao[]>(this.conexao.getServidor() + `/pelotao`);
    }

    /**
     * 
     * @description Serviço para cadastrar novo pelotão
     * @param {NovoPelotao} novoPelotao 
     * @return {Observable<NovoPelotao>}
     */
    public cadastrarPelotao(novoPelotao: NovoPelotao):Observable<NovoPelotao> {
      return this.http.post<NovoPelotao>(this.conexao.getServidor() + `/pelotao/cadastrar`, novoPelotao);
    }

}
