import { Component, OnInit, Input } from '@angular/core';
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
  @Input()
  data: any;
  hotSettings;
  colHeaders = [];
  result;
  constructor(
    private dataService: DataService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    const rowId = this.data.id;
    const payload = {};
    payload['id'] = rowId;
    this.dataService.getRow(payload).subscribe(
      res => {
        this.result = res;
        this.dataset.push(this.result.response);
        this.colHeaders = Object.keys(this.result.response);
        console.log(this.result);
      },
      error => {
        console.log(error);
        alert('Unable to fetch data');
      }
    );
  }
  edit(event) {
    const row = event.hotInstance.getSourceData();
    const payload = {};
    payload[tableMap[event.params[0][0][1]]] = event.params[0][0][3];
    payload['ID'] = row[0].id;
    console.log(payload);
    this.dataService.updateRow(payload).subscribe(res => {
        this.toastr.success('Successfully updated.');
      }, error => {
        event.hotInstance.undo();
        this.toastr.error('Sorry unable to save.');
      });
  }

  setWidth(event) {
    event.hotInstance.updateSettings({ width: 1200});
    event.hotInstance.updateSettings({});
    // event.hotInstance.updateHotTable();
  }
}


