import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Boat } from 'src/app/models/Boat';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  infoBoolean: boolean = false;
  emptyBoolean: boolean = false;
  tableBoolean: boolean = false;
  stripeCheckout: boolean = false;

  boatsArray: Boat[] = [];
  orderArray: any[]=[];
  userInfo = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    phone: new FormControl('', Validators.required),
  })

  subTotal: number =0;
  taxes: number;
  total: number = 0;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem("cartList")){
      this.boatsArray = JSON.parse(sessionStorage.getItem("cartList"));
      console.log(this.boatsArray);
      this.tableBoolean = true;
      } else {
        this.emptyBoolean = true;
    }
  }
  ngDoCheck(){
    this.getTotals();  

  }

  getTotals(){
    this.subTotal=0;
    for(let i =0; i< this.boatsArray.length; i++){
      this.subTotal += this.boatsArray[i].price;
    }
    this.taxes = this.subTotal * .08;
    this.total = this.subTotal + this.taxes;
  }

  delete(event){
    const id:string = event.path[3].children[0].innerText;
    console.log(id);
    console.log(event);
    this.boatsArray = this.boatsArray.filter(item => item.id !== id);
    console.log(this.boatsArray);
    sessionStorage.setItem("cartList", JSON.stringify(this.boatsArray));
     
  }

  checkOut(){
    console.log("check out");
    this.infoBoolean = true;
    this.tableBoolean = false;
  }

  infoSubmit(){
    this.stripeCheckout = true;
    this.infoBoolean = false;
    this.orderArray.push(this.userInfo.value);
    this.orderArray.push(this.boatsArray);
    console.log(this.orderArray);
    
  }

  stripeSubmit(){
    this.api.submitOrder(this.orderArray);
  }
}
