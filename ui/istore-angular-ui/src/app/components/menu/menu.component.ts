import { Component, OnInit } from '@angular/core';
import { AuthAPIService } from '../../services/security/auth-api.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {


  constructor(private authAPIService: AuthAPIService) { }

  ngOnInit() {
  }

}
