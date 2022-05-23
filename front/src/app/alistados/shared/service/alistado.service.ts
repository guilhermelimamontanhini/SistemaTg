import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { Alistados } from '../model/alistados.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlistadoService {

  readonly apiURL = 'http://localhost:8080';

  constructor( private http: HttpClient, private conexao: ConexaoService ) { 
  
  }

  public listarTodosAlistados():Observable<Alistados[]> {
    return this.http.get<Alistados[]>(this.apiURL + `/alistado`);
  }

}
