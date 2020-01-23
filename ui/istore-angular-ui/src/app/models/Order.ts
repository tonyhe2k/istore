import { Product } from './Product';
import { Payment } from './Payment';

export class Order {
    id:number;
    orderBy:string;
    orderTotal:number;
    items:Product[];
    payment:Payment;

    constructor(
            orderBy: string, 
            orderTotal: number, 
            items: Product[],
            payment: Payment) {
        this.orderBy = orderBy;
        this.orderTotal = orderTotal;
        this.items = items;
        this.payment = payment;
    }
}