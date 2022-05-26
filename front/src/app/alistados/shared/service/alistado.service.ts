import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { Alistados } from '../model/alistados.model';
import { Observable } from 'rxjs';
import { AlistadosFORM } from '../model/alistadoFORM.model';

@Injectable({
  providedIn: 'root'
})
export class AlistadoService {

  constructor( private http: HttpClient, private conexao: ConexaoService ) { 
  
  }

  public listarTodosAlistados():Observable<Alistados[]> {
    return this.http.get<Alistados[]>(this.conexao.getServidor() + `/alistado`);
  }

  public cadastrarAlistado(alistado: AlistadosFORM): Observable<AlistadosFORM> {
    return this.http.post<AlistadosFORM>(this.conexao.getServidor() + `/alistado/cadastrar`, alistado);
  }

}
