import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {Product} from '../classes/product';


@Injectable({
  providedIn: 'root'
})
export class ProductService {
  readonly rootURL = `${environment.backendURL}/products`;

  constructor(private http: HttpClient) {
  }

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.rootURL);
  }

}
