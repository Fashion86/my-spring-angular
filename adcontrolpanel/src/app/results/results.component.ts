import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {
  results: any;
  tabs: any[] = [];

  constructor(private dataService: DataService) {}

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
    console.log(row);
    const exist = this.tabs.find(a => a.audioSource === row.audioSource);
    if (!exist) {
      this.tabs.push(row);
    }
  }

  removeTab(row) {
    this.tabs = this.tabs.filter(a => a.audioSource !== row.audioSource);
  }
}
