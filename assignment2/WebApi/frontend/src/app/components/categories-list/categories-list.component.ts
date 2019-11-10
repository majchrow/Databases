import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../classes/category';

@Component({
  selector: 'app-categories-list',
  templateUrl: './categories-list.component.html',
  styles: []
})
export class CategoriesListComponent implements OnInit {

    @Output() categoryEmitter = new EventEmitter<number>();

    constructor(private categoryService: CategoryService) { }

    ngOnInit() {
        this.categoryService.refreshList();
    }

    populateForm(category: Category) {
        this.categoryService.formData = Object.assign({}, category);
    }

    onDelete(id: number) {
            this.categoryService.deleteCategory(id).subscribe(res => {
                this.categoryService.refreshList();
            });
    }

    onModify(id: number) {
        this.categoryEmitter.emit(id);
    }
}
