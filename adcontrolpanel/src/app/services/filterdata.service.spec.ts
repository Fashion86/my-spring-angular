import { TestBed, inject } from '@angular/core/testing';

import { FilterdataService } from './filterdata.service';

describe('FilterdataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FilterdataService]
    });
  });

  it('should be created', inject([FilterdataService], (service: FilterdataService) => {
    expect(service).toBeTruthy();
  }));
});
