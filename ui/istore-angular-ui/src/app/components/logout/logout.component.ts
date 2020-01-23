import { Component, OnInit } from '@angular/core';
import { AuthAPIService } from '../../services/security/auth-api.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
         private authAPIService : AuthAPIService
    ) { }

  ngOnInit() {
    this.authAPIService.logout()
  }

}
