import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {OrderService} from '../../services/order.service';
import {Order} from '../../classes/order';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styles: []
})
export class OrdersComponent implements OnInit {
  orders: Order[];

  @Output() orderViewEmitter = new EventEmitter<boolean>();

  constructor(private orderService: OrderService) {
  }

  ngOnInit() {
    this.getOrders();
  }

  private getOrders() {
    this.orderService.getOrders().subscribe(orders => this.orders = orders);
  }

  private onNewOrderClick() {
    this.orderViewEmitter.emit(true);
  }
}
