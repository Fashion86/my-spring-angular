import { Component, OnInit, Input, EventEmitter } from '@angular/core';
import * as Handsontable from 'handsontable';
import { DataService } from '../data.service';
import { ToastrService } from 'ngx-toastr';
import { tableMap } from '../table-map';

@Component({
  selector: 'app-channel-tab-content',
  templateUrl: './channel-tab-content.component.html',
  styleUrls: ['./channel-tab-content.component.css']
})
export class ChannelTabContentComponent implements OnInit {
  dataset: any[] = [];
  hotSettings;
  colHeaders = [];
  result;
  @Input() data: any;
  @Input() editaction: EventEmitter<any>;
  constructor(
    private dataService: DataService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    if (this.data) {
      const fileName = this.data.fileName;
      this.setDataSet(fileName);
    }
    if (this.editaction) {
      this.editaction.subscribe(res => {
        if (res.tab) {
          this.setDataSet(res.tab.fileName);
        } else {
          this.result = null;
          this.dataset = [];
          this.colHeaders = [];
        }

      });
    }
  }

  setDataSet(fileName) {
    this.dataset = [];
    this.colHeaders = [];
    this.dataService.getRow(fileName).subscribe(
      res => {
        this.result = res;
        if (this.result.response.length > 0) {
          this.dataset = this.result.response;
          this.colHeaders = Object.keys(this.result.response[0]);
        }
      },
      error => {
        console.log(error);
        alert('Unable to fetch data');
      }
    );
  }

  edit(event) {
    console.log(event)
    // const row = event.hotInstance.getSourceData();
    // const payload = {};
    // payload[tableMap[event.params[0][0][1]]] = event.params[0][0][3];
    // payload['ID'] = row[0].id;
    // console.log(payload);
    // this.dataService.updateRow(payload).subscribe(res => {
    //     this.toastr.success('Successfully updated.');
    //   }, error => {
    //     event.hotInstance.undo();
    //     this.toastr.error('Sorry unable to save.');
    //   });
  }

  setWidth(event) {
    event.hotInstance.updateSettings({ width: 1200});
    event.hotInstance.updateSettings({});
    // event.hotInstance.updateHotTable();
  }
}


