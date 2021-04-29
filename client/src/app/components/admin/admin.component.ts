import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/Order';
import { OrderDisplay } from 'src/app/models/OrderDisplay';
import { ApiService } from 'src/app/services/api.service';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  loginBoolean: boolean = false;
  errorBoolean: boolean = false;
  invalidBoolean: boolean = false;
  orderBoolean: boolean = true;

  userName: string;
  password: string;

  orders: Order[];
  orderDisplays: OrderDisplay[] = [];
  sortedOrderDisplays: OrderDisplay[]= [];

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.upcoming();
    
  }

  submit(){
    if(this.userName == undefined || this.password == undefined){
      this.errorBoolean = true;
    } else {
      this.api.login(this.userName).subscribe(res => {
        console.log(res);
        if(this.password == res){
          this.loginBoolean = false;
          this.orderBoolean = true;
        }else{
          this.invalidBoolean = true;
        }
      }, error =>{
        console.log(error);
        this.invalidBoolean = true;
        
      })
    }
  }

  all(){
      this.api.getAllOrders().subscribe(res=>{
      console.log(res);
      this.displayify(res);      
      console.log(this.orderDisplays);
      this.sort();
    })
  }

  upcoming(){
    this.api.getAllOrdersByDate().subscribe(res=>{
      console.log(res);
      this.displayify(res);      
      console.log(this.orderDisplays);
      this.sort();
      })

  }

  today(){
    
    this.sortedOrderDisplays = this.orderDisplays.filter(item => {          
     return new Date(item.date).getTime() > new Date().getTime() - (24 * 60 * 60 * 1000) && new Date(item.date).getTime() < new Date().getTime()})
    
  }

  sort(){
    this.sortedOrderDisplays = this.orderDisplays.sort((a:any, b:any)=>{
      return +new Date(a.date) - +new Date(b.date);
    })
  }

  displayify(orders){
    this.orderDisplays = [];
    for(let order of orders){
      for(let boat of order.boats){
      const display: OrderDisplay ={
        id: order.order_id,
        date: boat.date,
        time: boat.time,
        duration: boat.duration,
        boat: boat.boat,
        name: order.customer.firstName +" "+ order.customer.lastName,
        height: boat.height,
        weight: boat.weight,
        email: order.customer.email,
        phone: order.customer.phone
      }        
      this.orderDisplays.push(display);
    }
  }
  }
}
