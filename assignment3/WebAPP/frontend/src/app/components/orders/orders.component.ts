import {Component, OnInit} from '@angular/core';
import {OrderService} from '../../services/order.service';
import {Order} from '../../classes/order';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styles: []
})
export class OrdersComponent implements OnInit {

  orders: Order[];
  displayedColumns: string[] = ['invoiceID'];
  title = 'Your Orders';
  view = 0;

  constructor(private orderService: OrderService) {
  }

  ngOnInit() {
    this.getOrders();
  }

  private getOrders() {
    this.orderService.getOrders().subscribe(
      orders => this.orders = orders
    );
  }

  private onNewOrderClick() {
    this.title = 'New Order';
    this.view = 1;
  }

  addInvoice(order: Order) {
    if (order) {
      this.orders.push(order);
    }
    this.title = 'Your Orders';
    this.view = 0;
  }
}
