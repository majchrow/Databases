import { Component, OnInit, Input } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'app-product-form',
    templateUrl: './product-form.component.html',
    styles: []
})
export class ProductFormComponent implements OnInit {

    @Input() categoryID: number;

    constructor(private productService: ProductService) { }

    ngOnInit() {
        this.productService.resetForm();
    }


    onSubmit(form: NgForm) {
        if (form.value.productID == null)
            this.insertRecord(form);
        else
            this.updateRecord(form);
    }

    insertRecord(form: NgForm) {
        this.productService.postProduct(form.value, this.categoryID).subscribe(res => {
            this.productService.refreshList(this.categoryID);
        });
    }

    updateRecord(form: NgForm) {
        this.productService.putProduct(form.value, this.categoryID).subscribe(res => {
            this.productService.refreshList(this.categoryID);
        });
    }

}
