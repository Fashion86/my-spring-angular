// Author: Brinda Shallapille
// Last Date Modified: 13 sept 2018
// This is the page mainly used for search criteria:Date range,House number,LogAsrun,Selected stations

import { Component, OnInit} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { parseString } from 'xml2js';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})

export class LandingComponent implements OnInit  {
  // Modal display initially set to none
  display = 'none';
   daterange: any = {};
  options: any = {
    locale: { format: 'YYYY-MM-DD' },
    alwaysShowCalendars: false,
  };
  allStatios = [];
  moveOptions = [];
  removeOptions = [];
  removeData = [];
  moveData = [];
  searchForm = {
    stations: [],
    slectedStations: [],
    logs: false,
    asRun: false,
    houseNo: '',
    daterange: {
      start: '',
      end: ''
    },
  };

  constructor(
    private router: Router,
    private dataService: DataService,
    private http: HttpClient) {
    this.display = 'block';
  }

  ngOnInit() {
    this.http.get('assets/mock/Test.xml', { headers: new HttpHeaders()
      .set('Content-Type', 'text/xml')
      , responseType: 'text' }).subscribe((res) => {
        const xml = res;
        parseString(xml, (err, result) => {
          this.allStatios = this.formatData(result.channel.mainStation);
          this.moveOptions = this.allStatios.map(x => Object.assign({}, x));
          this.searchForm.stations = this.allStatios.map(x => Object.assign({}, x));
          this.sortAssending();

        });
    });
  }
// To format xml data for all main and sub stations
  formatData(data) {
    const formatedDate = [];
    data.forEach((row) => {
      const substations = [];
      row.substation.forEach(sub => {
        substations.push(
          {
            label: row.$.name,
            name: sub.$.name
          }
        );
      });
      const record = {
        label: row.$.name,
        values: substations
      };
      formatedDate.push(record);

    });
    return formatedDate;
  }

  // Used to hide the widget when X button is closed
  modalhide() {
    this.display = 'none';
  }

  // Navigate to under construction page for the pages not created
  Navigate() {
    this.router.navigate(['/underconstruction']);
  }

  LogOut() {
    this.router.navigate(['/login']);
  }

  // On click of search button in sidebar- show the search widget
  Search() {
    this.display = 'block';
  }

  moveAll() {
    this.moveOptions = [];
    this.removeOptions = this.allStatios.map(x => Object.assign({}, x));
    this.searchForm.slectedStations = this.allStatios.map(x => Object.assign({}, x));
    this.searchForm.stations = [];
    this.removeData = [];
    this.moveData = [];
    this.sortAssending();
  }

  removeAll() {
    this.removeOptions = [];
    this.moveOptions = this.allStatios.map(x => Object.assign({}, x));
    this.searchForm.stations = this.allStatios.map(x => Object.assign({}, x));
    this.searchForm.slectedStations = [];
    this.removeData = [];
    this.moveData = [];
    this.sortAssending();
  }

  moveSelectedItem(value) {
    if (value.length <= 0) {
      alert('Please select a station to move');
    }
    if (value.length > 0) {
      const formatedValue = this.formatedValueOption(value);
      formatedValue.forEach(val => {
        if (this.searchForm.slectedStations.find(a => a.label === val.label)) {
          this.searchForm.slectedStations.find(a => a.label === val.label).values.push(...val.values);
        } else {
          this.searchForm.slectedStations.push(val);
        }
        if (this.searchForm.stations.find(a => a.label === val.label)) {
          if (this.searchForm.stations.find(a => a.label === val.label).values.length > 0) {
            const arr = val.values.map(a => a.name);
            // tslint:disable-next-line:max-line-length
            this.searchForm.stations.find(a => a.label === val.label).values = this.searchForm.stations.find(a => a.label === val.label).values.filter(item => !arr.includes(item.name));
            if (this.searchForm.stations.find(a => a.label === val.label).values.length === 0) {
              this.searchForm.stations = this.searchForm.stations.filter(item => item.label !== val.label);
            }
          } else {
            this.searchForm.stations = this.searchForm.stations.filter(item => item.label !== val.label);
          }
        }
      });
      this.removeOptions = this.searchForm.slectedStations;
      this.moveOptions = this.searchForm.stations;
      this.removeData = [];
      this.moveData = [];
      this.sortAssending();
    }
  }

  removeSelectedItem(value) {
    if (value.length <= 0) {
      alert('Please select a station to move');
    }
    if ( value.length > 0) {
      const formatedValue = this.formatedValueOption(value);
      formatedValue.forEach(val => {
        if (this.searchForm.stations.find(a => a.label === val.label)) {
          this.searchForm.stations.find(a => a.label === val.label).values.push(...val.values);
        } else {
          this.searchForm.stations.push(val);
        }
        if (this.searchForm.slectedStations.find(a => a.label === val.label)) {
          if (this.searchForm.slectedStations.find(a => a.label === val.label).values.length > 0) {
            const arr = val.values.map(a => a.name);
            // tslint:disable-next-line:max-line-length
            this.searchForm.slectedStations.find(a => a.label === val.label).values = this.searchForm.slectedStations.find(a => a.label === val.label).values.filter(item => !arr.includes(item.name));
            if (this.searchForm.slectedStations.find(a => a.label === val.label).values.length === 0) {
              this.searchForm.slectedStations = this.searchForm.slectedStations.filter(item => item.label !== val.label);
            }
          } else {
            this.searchForm.slectedStations = this.searchForm.slectedStations.filter(item => item.label !== val.label);
          }
        }
      });
      this.removeOptions = this.searchForm.slectedStations;
      this.moveOptions = this.searchForm.stations;
      this.removeData = [];
      this.moveData = [];
      this.sortAssending();
    }
  }
  selectAllStationMove(label, value) {
    value.forEach(val => {
      if (!((this.moveData.find(a => a.label === label)) && (this.moveData.find(a => a.name === val.name)))) {
        this.moveData.push(val);
      }
    });
  }
  selectAllStationRemove(label, value) {
    value.forEach(val => {
      if (!((this.removeData.find(a => a.label === label)) && (this.removeData.find(a => a.name === val.name)))) {
        this.removeData.push(val);
      }
    });
  }
  checkOptionClick(event) {
    event.stopPropagation();
  }
  formatedValueOption(value) {
    const formatedData = [];
    value.forEach(val => {
      if (formatedData.find( a => a.label === val.label)) {
        formatedData.find(a => a.label === val.label).values.push(val);
      } else {
        formatedData.push({
          label: val.label,
          values: [val]
        });
      }
    });
    return formatedData;
  }
  sortAssending() {
    this.removeOptions = this.removeOptions.sort(this.compare);
    this.removeOptions.forEach(val => {
      val.values = val.values.sort(this.compare);
    });
    this.moveOptions = this.moveOptions.sort(this.compare);
    this.moveOptions.forEach(val => {
      val.values = val.values.sort(this.compare);
    });
  }
  compare(a, b) {
    if (a.label < b.label) {
      return -1;
    }
    if (a.label > b.label) {
      return 1;
    }
    return 0;
  }
  onSubmit() {
    console.log(this.searchForm);
    this.dataService.setFormData(this.searchForm);
    this.router.navigate(['results']);
  }

  selectedDate(value: any, datepicker?: any) {
    // or manupulate your own internal property
    this.searchForm.daterange.start = value.start.format('YYYY-MM-DD');
    this.searchForm.daterange.end = value.end.format('YYYY-MM-DD');
  }
}







