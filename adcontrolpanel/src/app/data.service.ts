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

  getResult() {
    this.filters = JSON.parse(localStorage.getItem('filter'));
    return this.http.post(this.baseURL + '/getAppRecordBtwDates', this.filters);
  }

  getRow(filename) {
    const httpParams = new HttpParams()
      .append('filename', filename);
      return this.http.post(this.baseURL + '/getAppRow', httpParams);
  }

  updateRow(items: any) {
      return this.http.post(this.baseURL + '/updateAppRow', items);
  }
}
