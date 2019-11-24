import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-jumbotron',
  templateUrl: './jumbotron.component.html',
  styles: []
})
export class JumbotronComponent implements OnInit {

  title = 'Your Orders';
  view = 0;
  constructor() {
  }

  ngOnInit() {
  }

  changeView(newOrder: boolean) {
    if (newOrder) {
      this.title = 'New Order';
    } else {
      this.title = 'Order Details';
    }
    this.view = 1;
  }

}
