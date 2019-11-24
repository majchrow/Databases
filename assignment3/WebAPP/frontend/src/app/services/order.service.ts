import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Order} from '../classes/order';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class OrderService {
  readonly rootURL = `${environment.backendURL}/orders`;

  constructor(private http: HttpClient) {
  }

  public getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.rootURL);
  }

  public postInvoice(data): Observable<Order> {
    console.log(data);
    return this.http.post<Order>(this.rootURL, data);
  }
}
