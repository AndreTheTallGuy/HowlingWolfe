import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Boat } from '../models/Boat';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  
  private headers = { headers: new HttpHeaders().set('Content-Type', 'application/json') }

  constructor(private http: HttpClient) { }

  public submitOrder(order: any[]):Observable<any>{
    return this.http.post(`http://localhost:8080/orders/post`, order, this.headers);
  }
}
