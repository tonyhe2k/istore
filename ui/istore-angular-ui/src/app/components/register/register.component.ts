import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthAPIService } from '../../services/security/auth-api.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  email = ""
  password = ""
  username = ""
  firstName = ""
  lastName = ""

  errorMessage = "invalid entry"
  invalidRegistration = false

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authAPIService : AuthAPIService
  ) { }

  ngOnInit() {
  }

  handelRegistration() {
    this.authAPIService.register(this.email, this.password, this.username, this.firstName, this.lastName).subscribe(
      (data: HttpResponse<any>) => {
        console.log(data)

        this.router.navigate(["login"])
        this.invalidRegistration = false
      }, 
      error => {
        console.log(error)
        this.invalidRegistration = true
      }
    )
    
  }
}
