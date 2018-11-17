import { Injectable, isDevMode } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import * as moment from 'moment';

@Injectable()
export class DataService {
  formData: any;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) {}

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
    console.log(this.formData);
    const payload = {
      AsRun: this.formData.asRun,
      daterange: {
        start: this.formData.daterange.start,
        end: this.formData.daterange.end
      },
      houseNo: this.formData.houseNo,
      logs: this.formData.logs,
      stations: this.formData.stations,
      channels: this.formData.stations,
      Filetype: ''
    };
    if (isDevMode()) {
    return this.http.get('../assets/mock/response_mock.json');
    } else {
      return this.http.post(
        'http://localhost:8080/spring-rest-service/rest/sample/getAppRecordBtwDates',
        this.formData,
        this.httpOptions
      );
    }
  }

  getRow(payload) {
    if (isDevMode()) {
      return this.http.get('../assets/mock/row.json');
    } else {
      return this.http.post('http://localhost:8080/spring-rest-service/rest/sample/getAppRow', payload, this.httpOptions);
    }
  }

  updateRow(payload) {
    if (isDevMode()) {
      return this.http.get('../assets/mock/response_mock.json');
    } else {
      return this.http.post('http://localhost:8080/spring-rest-service/rest/sample/updateAppRow', payload, this.httpOptions);
    }
  }
}
