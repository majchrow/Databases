import { Component, OnInit, Input } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../classes/product';

@Component({
  selector: 'app-products-list',
    templateUrl: './products-list.component.html',
    styles: []
})
export class ProductsListComponent implements OnInit {

    @Input() categoryID: number;

    constructor(private productService: ProductService) { }

    ngOnInit() {
        this.productService.refreshList(this.categoryID);
    }

    populateForm(product: Product) {
        this.productService.formData = {
            productID: product.productID,
            name: product.name,
            unitsInStock: product.unitsInStock,
            unitprice: product.unitprice
        };
    }

    onDelete(id: number) {
        this.productService.deleteProduct(id).subscribe(res => {
            this.productService.refreshList(this.categoryID);
        });
    }

}
