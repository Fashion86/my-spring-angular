import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class FilterdataService {


  public filter = new BehaviorSubject<any>({});
  public filter$ = this.filter.asObservable();
  setedfilter: any;

  constructor() {
    this.setedfilter = {};
  }

  public getAsyncFilter(): any {
    return this.filter$;
  }

  public setFilter(filter: any): void {
    this.setedfilter = filter;
    this.filter.next(filter);
  }

  public getFilter(): any {
    return this.setedfilter;
  }

  public clearFilter(): void {
    this.filter.next({});
  }

}
