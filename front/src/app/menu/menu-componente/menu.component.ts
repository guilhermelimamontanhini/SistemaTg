import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
  })
export class MenuComponente implements OnInit {

  constructor(private rotas: Router) { }

  ngOnInit(): void {

  }

  public redirecionar(url: String): void {
    this.rotas.navigate([url]);
    // setTimeout(() => {
    //   this.fecharMenu();
    // } , 300);
  }
      
}