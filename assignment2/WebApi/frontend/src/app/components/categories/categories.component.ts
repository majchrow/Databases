import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styles: []
})
export class CategoriesComponent implements OnInit {

  title: string = "Category Registration";
  categoryID: number;
  constructor() { }

  ngOnInit() {
  }

    assignID(id: number) {
        this.categoryID = id;
        this.title = "Product Registration";
    }

    onHomeClick(event: any) {
        this.title = "Category Registration";
        this.categoryID = null;
    }

}
