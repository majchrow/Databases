import {Product} from './product';

export class Order {
    orderID: number;
    quantity = 0;
    orderPrice = 0;
    product: Product[];
}
