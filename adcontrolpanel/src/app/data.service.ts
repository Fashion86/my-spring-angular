import { Injectable, isDevMode } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import * as moment from 'moment';
import { FilterdataService } from './services/filterdata.service';

@Injectable()
export class DataService {
  formData: any;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  filters: any;
  baseURL = 'http://localhost:8080/spring-rest-service/rest/sample';
  constructor(private http: HttpClient,
              private filterService: FilterdataService) {
    this.filters = {};
  }

  setFormData(data) {
    this.formData = data;
    if (
      this.formData.daterange.start !== '' &&
      this.formData.daterange.end !== ''
    ) {
      this.formData.daterange.start = moment(
        this.formData.daterange.start,
        'YYYY-MM-DD'
      ).format('DD-MM-YYYY');
      this.formData.daterange.end = moment(
        this.formData.daterange.end,
        'YYYY-MM-DD'
      ).format('DD-MM-YYYY');
    }
    this.formData.houseNo = this.formData.houseNo.split('-');
    const stations = [];
    const channels = [];
    this.formData.slectedStations.forEach(element => {
      stations.push(element.label);
      element.values.forEach(value => {
        channels.push(value.name);
      });
    });
    this.formData.stations = stations;
    this.formData.stations = channels;
  }
  getResult() {
    this.filters = JSON.parse(localStorage.getItem('filter'));
    // const httpParams = new HttpParams()
    //   .append('filter', 'fff');
    // const payload = {
    //   AsRun: this.formData.asRun,
    //   daterange: {
    //     start: this.formData.daterange.start,
    //     end: this.formData.daterange.end
    //   },
    //   houseNo: this.formData.houseNo,
    //   logs: this.formData.logs,
    //   stations: this.formData.stations,
    //   channels: this.formData.stations,
    //   Filetype: ''
    // };
    // if (isDevMode()) {
    // return this.http.get('../assets/mock/response_mock.json');
    // } else {
    // const daterange = {
    //   start: this.filters.start,
    //   end: this.filters.end,
    // };

    const httpParams = new HttpParams()
      .append('start', this.filters.start)
      .append('end', this.filters.end);
      return this.http.post(this.baseURL + '/getAppRecordBtwDates', httpParams);
  }

  getRow(filename) {
    // if (isDevMode()) {
    //   return this.http.get('../assets/mock/row.json');
    // } else {
    const httpParams = new HttpParams()
      .append('filename', filename);
      return this.http.post(this.baseURL + '/getAppRow', httpParams);
    // }
  }

  updateRow(payload) {
    if (isDevMode()) {
      return this.http.get('../assets/mock/response_mock.json');
    } else {
      return this.http.post(this.baseURL + '/updateAppRow', payload, this.httpOptions);
    }
  }
}
