import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChannelTabContentComponent } from './channel-tab-content.component';

describe('ChannelTabContentComponent', () => {
  let component: ChannelTabContentComponent;
  let fixture: ComponentFixture<ChannelTabContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChannelTabContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChannelTabContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
