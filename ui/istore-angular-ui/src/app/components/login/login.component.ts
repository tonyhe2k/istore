import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthAPIService } from '../../services/security/auth-api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = ""
  password = ""
  errorMessage = "invalid credentials"
  invalidLogin = false
  returnUrl: string

  // Dependency Injection
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authAPIService : AuthAPIService
  ) { }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  handelLogin() {
    this.authAPIService.doAuth(this.email, this.password).subscribe(
      (data: HttpResponse<any>) => {
        console.log(this.returnUrl)
        console.log(data)

        sessionStorage.setItem('authenticatedUser', `${data.headers.get('userId')}`)
        sessionStorage.setItem('token', `Bearer ${data.headers.get('token')}`)

        if(this.returnUrl === '/') {
          this.router.navigate(["products"])
        } else {
          this.router.navigateByUrl(this.returnUrl);
        }
        this.invalidLogin = false
      }, 
      error => {
        console.log(error)
        this.invalidLogin = true
      }
    )
    
  }
}
