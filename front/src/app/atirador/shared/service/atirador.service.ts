import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { Observable } from 'rxjs';
import { Atirador } from '../model/atirador.model';

@Injectable({
  providedIn: 'root'
})
export class AtiradorService {

  constructor(private http: HttpClient, private conexao: ConexaoService) { }

  public listarTodosAlistados():Observable<Atirador[]> {
    return this.http.get<Atirador[]>(this.conexao.getServidor() + `/atirador`);
  }

}
