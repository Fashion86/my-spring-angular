import { Component, OnInit, OnChanges } from "@angular/core";
import * as $ from 'jquery';
import * as bootstrap from 'bootstrap';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';

  show = false;
  urlVal: any;

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    this.router.events.subscribe((val) => {
      if (val.constructor.name === 'NavigationStart') {
        this.urlVal = val;
        console.log(this.urlVal.url);
        this.show = (this.urlVal.url === '/' || this.urlVal.url === '/login' || this.urlVal.url === undefined) ? false : true;
      }
    });
  }

  ngOnInit() {
  }

}
