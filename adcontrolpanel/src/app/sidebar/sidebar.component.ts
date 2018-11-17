import { Component, OnInit } from '@angular/core';

import * as $ from "jquery";
import * as bootstrap from "bootstrap";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    $('#collapse-icon').addClass('fa-angle-double-left');
    $('[data-toggle=sidebar-colapse]').click(() => {
      this.sidebarCollapse();
    });
  }

  sidebarCollapse() {
    $('.menu-collapsed').toggleClass('d-none');
    $('.sidebar-submenu').toggleClass('d-none');
    $('.submenu-icon').toggleClass('d-none');
    $('#sidebar-container').toggleClass('sidebar-expanded sidebar-collapsed');
    const separatorTitle = $('.sidebar-separator-title');
    if (separatorTitle.hasClass('d-flex')) {
      separatorTitle.removeClass('d-flex');
    } else {
      separatorTitle.addClass('d-flex');
    }
    $('#collapse-icon').toggleClass('fa-angle-double-left fa-angle-double-right');
  }
  logout() {
    this.router.navigate(['']);
  }
  landing() {
    this.router.navigate(['/landing']);
    location.reload();
  }
  underconstruction() {
    this.router.navigate(['/underconstruction']);
  }

}
