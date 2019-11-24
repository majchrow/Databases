import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Product} from '../../classes/product';
import {ProductService} from '../../services/product.service';
import {Order} from '../../classes/order';
import {OrderService} from '../../services/order.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styles: []
})
export class ProductsComponent implements OnInit {

  products: Product[];
  displayedColumns: string[] = ['ID', 'name', 'unitsOnStock', 'unitPrice'];

  @Output() invoiceEmitter = new EventEmitter<Order>();

  constructor(
    private productService: ProductService,
    private orderService: OrderService
  ) {
  }

  createArray(n: number) {
    return [...Array(n + 1).keys()];
  }

  ngOnInit() {
    this.getOrders();
  }

  private unitExists(product: Product): boolean {
    return product.unitsOnStock > 0;
  }

  private correctOrder(): boolean {
    return this.products.some(product => product.selectedUnit > 0);
  }

  private placeOrder() {
    const preparedProducts = [];
    this.products.filter(product => product.selectedUnit > 0).forEach(product => {
      preparedProducts.push({
        productID: product.productID,
        quantity: product.selectedUnit
      });
    });
    this.orderService.postInvoice(preparedProducts).subscribe(result => {
      console.log(result);
      this.invoiceEmitter.emit(result);
    }, error => {
      alert('Failed to place order');
      this.invoiceEmitter.emit(null);
    });
  }

  private cancelOrder() {
    this.invoiceEmitter.emit(null);
  }

  private getOrders() {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
      this.products.forEach(product => product.selectedUnit = 0);
    });
  }
}
