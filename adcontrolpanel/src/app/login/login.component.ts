import { Component, OnInit, Directive } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LandingComponent } from '../landing/landing.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
username = '';
password = '';
allowRoute =true;


  constructor(private router: Router) {

  }
/// This logic will be removed once validated against database
 OnpasswordValidate() {
   if (this.username === 'acptest' && this.password === 'password') {
   this.router.navigate(['/landing']);
   this.allowRoute = true;
     
   } else {
       this.allowRoute = false;
      this.password='';
   }
   
  }
  

   
  ngOnInit() {
  }


}
