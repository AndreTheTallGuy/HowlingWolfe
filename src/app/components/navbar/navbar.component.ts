import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  quantity?: number;

  constructor() { }

  ngOnInit(): void {
    
  }
  ngDoCheck(){
    if(sessionStorage.getItem("cartList")){
        this.quantity = JSON.parse(sessionStorage.getItem("cartList")).length;        
      }
  }
  click(){
    console.log("click");
    
  }

}
