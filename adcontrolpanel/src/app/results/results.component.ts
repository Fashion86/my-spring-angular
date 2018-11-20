import { Component, OnInit, EventEmitter } from '@angular/core';
import { DataService } from '../data.service';
import {ActivatedRoute, Router} from '@angular/router';
import * as Handsontable from 'handsontable';

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
              private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.dataService.getResult().subscribe(
      res => {
        this.results = res;
        console.log(this.results);
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
