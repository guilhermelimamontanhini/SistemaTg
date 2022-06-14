import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConexaoService } from 'src/app/conexao/conexao.service';
import { DataGuarda } from 'src/app/guardas/shared/model/dataGuarda.model';

@Injectable({
  providedIn: 'root'
})
export class RelatoriosService {

  constructor( private http: HttpClient, private conexao: ConexaoService ) { }

  public gerarRelatorioTodosAtiradores(): Observable<any> {
    return this.http.get<any>(this.conexao.getServidor() + `/todosAtiradores`, { responseType: 'blob' as 'json' });
  }

  public gerarRelatorioPontosAtiradores(): Observable<any> {
    return this.http.get<any>(this.conexao.getServidor() + `/pontosAtiradores`, { responseType: 'blob' as 'json' });
  }

  public gerarRelatorioAlistadosRefratarios(tipo: number): Observable<any> {
    return this.http.get<any>(this.conexao.getServidor() + `/todosAlistados/` + tipo, { responseType: 'blob' as 'json' });
  }

  public gerarRelatorioDespensados(): Observable<any> {
    return this.http.get<any>(this.conexao.getServidor() + `/todosDespensados`, { responseType: 'blob' as 'json' });
  }

  public gerarRelatorioDesligados(): Observable<any> {
    return this.http.get<any>(this.conexao.getServidor() + `/todosDesligados`, { responseType: 'blob' as 'json' });
  }

  public gerarRelatorioGuardaDia(data: DataGuarda): Observable<any> {
    return this.http.post<any>(this.conexao.getServidor() + `/todosIntegrantesGuarda`, data, { responseType: 'blob' as 'json' });
  }

  public gerarRelatorioMembrosPelotao(numero: number): Observable<any> {
    return this.http.get<any>(this.conexao.getServidor() + `/membrosPelotao/` + numero, { responseType: 'blob' as 'json' });
  }

  public gerarRelatorioAlunosCurso(): Observable<any> {
    return this.http.get<any>(this.conexao.getServidor() + `/todosAlunos`, { responseType: 'blob' as 'json' });
  }

  public gerarRelatorioMonitores(): Observable<any> {
    return this.http.get<any>(this.conexao.getServidor() + `/todosMonitores`, { responseType: 'blob' as 'json' });
  }

  public gerarRelatorioAtiraram(): Observable<any> {
    return this.http.get<any>(this.conexao.getServidor() + `/todosAtiraram`, { responseType: 'blob' as 'json' });
  }

}
