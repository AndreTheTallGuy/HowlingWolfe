import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Boat } from 'src/app/models/Boat';
import { Time } from 'src/app/models/Time';


@Component({
  selector: 'app-rent',
  templateUrl: './rent.component.html',
  styleUrls: ['./rent.component.css']
})
export class RentComponent implements OnInit {

  boatList: string[] = [];
  selectedBoat?: string;
  boatInfo: Boat;
  
  boatBoolean: boolean = true;
  dateBoolean: boolean = false;
  addedToCartBoolean: boolean = false;
  errorBoolean: boolean = false;

  dateForm = new FormGroup({
    date: new FormControl('', Validators.required),
    time: new FormControl('', Validators.required),
    duration: new FormControl('', Validators.required),
    height: new FormControl('', Validators.required),
    weight: new FormControl('', Validators.required)
  })
  
  date: any;
  time: any;
  duration: any;
  height: any;
  weight: any;

  cartArray?: Boat[] = [];
  cartList?: string;
  
  minDate: Date = new Date;

  timeOptions: Time[];

  constructor( ) { }
  
  ngOnInit(): void {
    this.minDate.setDate(this.minDate.getDate() + 1);

    
  }

  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    // Prevent Saturday and Sunday from being selected.
    return day !== 1 && day !== 2;
  }

  reset(){
    this.time = "";
  }
 
  boatSelection(event){
    console.log(event.srcElement.alt);
    this.selectedBoat = event.srcElement.alt;
    this.boatBoolean = false;
    this.dateBoolean = true;
  }

  submitDate(){
    if(this.height == undefined || this.weight == undefined || this.date == undefined || this.duration == undefined || this.time == undefined){
      this.errorBoolean = true;
    }else{
      this.boatInfo = {
        boat: this.selectedBoat,
        height: this.height,
        weight: this.weight,
        date: this.date,
        duration: this.duration,
        time: this.time,
      }
      console.log(this.boatInfo);
      this.addToSessionStorage(this.boatInfo);
      this.dateBoolean = false;
      this.addedToCartBoolean = true;
    }
  }

  addToSessionStorage(boat:Boat){
    // Check session storage for already added boats
    if(sessionStorage.getItem("cartList")){
      //assign them to cartList
      this.cartList = sessionStorage.getItem("cartList");
      // initialize var with current cartlist
      const boatsInCart = JSON.parse(this.cartList);
      // push each boat into the cartArray
      for(let i=0; i < boatsInCart.length; i++){
        this.cartArray.push(boatsInCart[i]);
      }
      //push new boat into cartArray
      this.cartArray.push(boat);
      //convert cartArray to string
      this.cartList = JSON.stringify(this.cartArray);
      // add string to Session Storage
      sessionStorage.setItem("cartList", this.cartList);
      // clear the cartArray
      this.cartArray = [];
    } else {
      //push new boat to cartArray
      this.cartArray.push(boat);
      //add strigified version of cartArray to session storage
      sessionStorage.setItem("cartList", JSON.stringify(this.cartArray));
      //clear cartArray
      this.cartArray = [];
    }

  }

  addAnotherBoat(){
    this.boatInfo.height = "";
    this.boatInfo.weight = "";
    this.addedToCartBoolean = false;
    this.boatBoolean = true;
  }

  timeResolver(){
    
    const split = this.date.toString().split(" ")[0];
    console.log(split);
    
    if(split == "Wed" || split  == "Thu" || split  == "Fri"){
      if(this.duration == "2"){
        this.timeOptions = [
          {time:"9am", value:"9am"},
          {time:"10am", value:"10am"},
          {time:"11am", value:"11am"},
          {time:"12pm", value:"12pm"},
          {time:"1pm", value:"1pm"},
          {time:"2pm", value:"2pm"},
          {time:"3pm", value:"3pm"},
          {time:"4pm", value:"4pm"},
        ];
      }else if(this.duration == "4"){
        this.timeOptions = [
          {time:"9am", value:"9am"},
          {time:"10am", value:"10am"},
          {time:"11am", value:"11am"},
          {time:"12pm", value:"12pm"},
          {time:"1pm", value:"1pm"},
          {time:"2pm", value:"2pm"}
        ];
      }else if(this.duration == "6"){
        this.timeOptions =[
          {time:"9am", value:"9am"},
          {time:"10am", value:"10am"},
          {time:"11am", value:"11am"},
          {time:"12pm", value:"12pm"},
        ]
      }
    }else if(split == "Sat"){
      if(this.duration == "2"){
        this.timeOptions = [
          {time:"9am", value:"9am"},
          {time:"10am", value:"10am"},
          {time:"11am", value:"11am"},
          {time:"12pm", value:"12pm"},
          {time:"1pm", value:"1pm"},
          {time:"2pm", value:"2pm"},
          {time:"3pm", value:"3pm"},
        ];
      } else if(this.duration == "4"){
        this.timeOptions = [
          {time:"9am", value:"9am"},
          {time:"10am", value:"10am"},
          {time:"11am", value:"11am"},
          {time:"12pm", value:"12pm"},
          {time:"1pm", value:"1pm"},
        ];
      } else if(this.duration == "6"){
        this.timeOptions = [
          {time:"9am", value:"9am"},
          {time:"10am", value:"10am"},
          {time:"11am", value:"11am"},
        ];
      }
    }else if(split == "Sun"){
      if(this.duration == "2"){
        this.timeOptions = [
          {time:"9am", value:"9am"},
          {time:"10am", value:"10am"},
          {time:"11am", value:"11am"},
          {time:"12pm", value:"12pm"},
          {time:"1pm", value:"1pm"},
          {time:"2pm", value:"2pm"},
        ];
      } else if(this.duration == "4"){
        this.timeOptions = [
          {time:"9am", value:"9am"},
          {time:"10am", value:"10am"},
          {time:"11am", value:"11am"},
          {time:"12pm", value:"12pm"},
        ];
      } else if(this.duration == "6"){
        this.timeOptions = [
          {time:"9am", value:"9am"},
          {time:"10am", value:"10am"},
        ];
      }
    }
  }
}
