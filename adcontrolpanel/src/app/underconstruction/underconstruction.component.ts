import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-underconstruction',
  templateUrl: './underconstruction.component.html',
  styleUrls: ['./underconstruction.component.css']
})
export class UnderconstructionComponent implements OnInit {

  constructor(  private router: Router) { }

  ngOnInit() {
  }
  
   // Navigate to underconstruction page for the pages not created
  Navigate() {
    this.router.navigate(['/underconstruction']);
  }

  LogOut() {
    this.router.navigate(['/login']);
  }

  // On click of search button in sidebar- show the search widget
  Search() {
     this.router.navigate(['/landing']);
  }

}
