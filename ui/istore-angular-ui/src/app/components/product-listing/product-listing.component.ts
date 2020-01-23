import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from '../../models/Product'
import { Order } from 'src/app/models/Order';
import { AuthAPIService } from '../../services/security/auth-api.service';
import { ProductService } from '../../services/product.service'
import { OrderService } from '../../services/order.service'
import { Payment } from 'src/app/models/Payment';

@Component({
  selector: 'app-product-listing',
  templateUrl: './product-listing.component.html',
  styleUrls: ['./product-listing.component.css']
})
export class ProductListingComponent implements OnInit {
  products: Product[];
  order: Order;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService,
    private orderService: OrderService,
    private authAPIService : AuthAPIService
  ) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
    });
  }

  executeOrder() {
    console.log("ProductListingComponent : execute order")

    // mocking object
    let items: Product[] = [
      new Product(12345, 'superman toy', 'white palm size superman tooy', 19.99, 1),
      new Product(12346, 'superwoman', 'Superwoman Toy', 19.99, 2)
    ];

    let payment: Payment = new Payment("CC", "1234-5678-9012-3456", "1220", 123);
    let order: Order = new Order (
      this.authAPIService.getAuthenticatedUser(),
      19.99 + 19.99,
      items,
      payment
    );
    console.log("ProductListingComponent : order")
    console.log(order);

    console.log("ProductListingComponent : calling service")

    this.orderService.executeOrder(order).subscribe(
      data => {
        console.log(data);
        this.order = data;
        this.router.navigate(["order", this.order.id])
      }
    )
   

  }
}
