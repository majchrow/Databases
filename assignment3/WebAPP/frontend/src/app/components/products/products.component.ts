import {Component, OnInit} from '@angular/core';
import {Product} from '../../classes/product';
import {ProductService} from '../../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styles: []
})
export class ProductsComponent implements OnInit {

  products: Product[];

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.getOrders();
  }

  private getOrders() {
    this.productService.getProducts().subscribe(products => this.products = products);
  }
}
