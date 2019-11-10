import { Component, OnInit} from '@angular/core';
import { NgForm } from '@angular/forms';
import { CategoryService } from '../../services/category.service';


@Component({
    selector: 'app-category-form',
    templateUrl: './category-form.component.html',
    styles: []
})
export class CategoryFormComponent implements OnInit {
  

    constructor(private categoryService: CategoryService) { }

    ngOnInit() {
        this.categoryService.resetForm();
    }

    onSubmit(form: NgForm) {
        
        if (form.value.categoryID == null)
            this.insertRecord(form);
        else
            this.updateRecord(form);
    }

    insertRecord(form: NgForm) {
        this.categoryService.postCategory(form.value).subscribe(res => {
            this.categoryService.refreshList();
        });
    }

    updateRecord(form: NgForm) {
        this.categoryService.putCategory(form.value).subscribe(res => {
            this.categoryService.refreshList();
        });
    }
}
