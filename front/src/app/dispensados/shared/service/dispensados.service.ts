import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { Dispensados } from '../model/dispensados.model';

@Injectable({
  providedIn: 'root'
})
export class DispensadosService {

  constructor(private http: HttpClient, private conexao: ConexaoService) { }

  public listarTodosDispensados():Observable<Dispensados[]> {
    return this.http.get<Dispensados[]>(this.conexao.getServidor() + `/dispensado`);
  }

  public deletarDispensado(id: Number): any {
    return this.http.delete(this.conexao.getServidor() + `/dispensado/deletar/` + id);
  }

}
