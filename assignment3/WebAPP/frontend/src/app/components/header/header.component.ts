import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: []
})
export class HeaderComponent implements OnInit {

    @Input() title: string;
    @Output() homeClick = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }

    onClick() {
        this.homeClick.emit("clicked");
    }

}
