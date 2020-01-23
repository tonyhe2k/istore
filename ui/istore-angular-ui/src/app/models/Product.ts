export class Product {
    productId:number;
    name:string;
    description:string;
    price:number;
    quantity:number;

    constructor(
        productId: number, 
        name: string, 
        description: string,
        price: number,
        quantity: number) {
    this.productId = productId;
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    }
}