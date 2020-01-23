import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthAPIService } from './security/auth-api.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

  constructor(private router: Router, private authAPIService: AuthAPIService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

  if (this.authAPIService.isUserLoggedIn()){
    return true
  }  

  //this.router.navigate(['login'])
  this.router.navigate(['login'], { queryParams: { returnUrl: state.url }});

  return false
  
}


}
