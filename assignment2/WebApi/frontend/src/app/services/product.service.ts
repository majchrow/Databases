import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../classes/product';
import { NgForm } from '@angular/forms';


@Injectable({
  providedIn: 'root'
})
export class ProductService {
    readonly rootURL = `${environment.backendURL}/Products`;

    formData: Product;
    productList: Product[];

    constructor(private http: HttpClient) { }

    resetForm(form?: NgForm) {
        if (form != null)
            form.resetForm();
        this.formData = {
            productID: null,
            name: '',
            unitsInStock: 0,
            unitprice: 0
        }
    }

    refreshList(categoryID: number) {
        this.http.get<Product[]>(`${this.rootURL}/byCategory${categoryID}`)
            .toPromise().then(res => {this.productList = res});
        this.resetForm();
    }

    public postProduct(data: Product, categoryID: number): Observable<Product> {
        let obj = {
            Name: data.name,
            UnitsInStock: data.unitsInStock,
            Unitprice: data.unitprice,
            CategoryID: categoryID
        }
        return this.http.post<Product>(this.rootURL, obj);
    }

    public putProduct(data: Product, categoryID: number): Observable<any> {
        let obj = {
            ProductID: data.productID,
            Name: data.name,
            UnitsInStock: data.unitsInStock,
            Unitprice: data.unitprice,
            CategoryID: categoryID
        }
        return this.http.put<any>(`${this.rootURL}/${data.productID}`, obj);
    }

    public deleteProduct(id: number): Observable<Product> {
        return this.http.delete<Product>(`${this.rootURL}/${id}`);
    }
}
