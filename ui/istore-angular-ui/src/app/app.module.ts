import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ErrorComponent } from './components/error/error.component';
import { FooterComponent } from './components//footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { MenuComponent } from './components//menu/menu.component';
import { ProductItemComponent } from './components/product-item/product-item.component';
import { ProductListingComponent } from './components/product-listing/product-listing.component';

import { HttpIntercepterBasicAuthServiceService } from './services/security/http-intercepter-basic-auth-service.service';
import { OrderComponent } from './components/order/order.component';

@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    MenuComponent,
    ProductItemComponent,
    ProductListingComponent,
    OrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterBasicAuthServiceService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
