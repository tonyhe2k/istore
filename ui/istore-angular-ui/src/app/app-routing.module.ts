import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RouteGuardService } from './services/route-guard.service';

import { LoginComponent } from './components/login/login.component';
import { ErrorComponent } from './components/error/error.component';
import { LogoutComponent } from './components/logout/logout.component';
import { OrderComponent } from './components/order/order.component';
import { ProductListingComponent } from './components/product-listing/product-listing.component';

const routes: Routes = [
  { path : '', component : LoginComponent},
  { path : 'login', component : LoginComponent},
  { path : 'order/:orderId', component : OrderComponent, canActivate:[RouteGuardService]},
  { path : 'products', component : ProductListingComponent, canActivate:[RouteGuardService]},
  { path : 'logout', component : LogoutComponent},

  { path : '**', component : ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
