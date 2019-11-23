import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Category } from '../classes/category';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})

export class CategoryService {
    readonly rootURL = `${environment.backendURL}/Categories`;
    formData: Category;
    categoryList: Category[];

    constructor(private http: HttpClient) { }

    resetForm(form?: NgForm) {
        if (form != null)
            form.resetForm();
        this.formData = {
            categoryID: null,
            name: '',
            description: ''
        }
    }

    refreshList() {
        this.http.get<Category[]>(this.rootURL)
            .toPromise().then(res => this.categoryList = res);
        this.resetForm();
    }

    public postCategory(data: Category): Observable<Category> {
        let obj = {
            Name: data.name,
            Description: data.description
        }
        return this.http.post<Category>(this.rootURL, obj);
    }

    public putCategory(data: Category): Observable<Category> {
        let obj = {
            CategoryID: data.categoryID,
            Name: data.name,
            Description: data.description
        }
        return this.http.put<Category>(`${this.rootURL}/${data.categoryID}`, obj);
    }

    public deleteCategory(id: number): Observable<Category> {
        return this.http.delete<Category>(`${this.rootURL}/${id}`);
    }
}
