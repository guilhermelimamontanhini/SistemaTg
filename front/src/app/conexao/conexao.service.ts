import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConexaoService {

  public urlBack: string = 'http://localhost:8080';

  constructor() { }

  public getServidor(): string {
    return this.urlBack;
  }
}