import { Component, OnInit, EventEmitter } from '@angular/core';
import { DataService } from '../data.service';
import {ActivatedRoute, Router} from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {
  results: any;
  tabs: any[] = [];
  editaction = new EventEmitter<any>();
  activetab: any;
  constructor(private dataService: DataService,
              private route: ActivatedRoute,
              private toastr: ToastrService) {

  }

  ngOnInit() {
    this.dataService.getResult().subscribe(
      res => {
        this.results = res;
        if (this.results.response.length < 1) {
          this.toastr.warning('No Results Found', 'Search Result');
        }
      },
      error => {
        console.log(error);
        alert('Unable to fetch data');
      }
    );
  }

  openTab(row) {
    const exist = this.tabs.find(a => a.id === row.id);
    if (!exist) {
      this.tabs.push(Object.assign({}, row));
      this.onSelectTab(row);
    }
  }

  onSelectTab(row) {
    this.activetab = row;
    this.editaction.emit({tab: this.activetab});
  }

  removeTab(row) {
    this.tabs = this.tabs.filter(a => a.id !== row.id);
    if (this.tabs.length === 1) {
      this.onSelectTab(this.tabs[0]);
    } else if (this.tabs.length === 0) {
      this.onSelectTab(null);
    }
  }
}
