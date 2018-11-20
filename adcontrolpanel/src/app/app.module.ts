import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Directive } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';

import { HotTableModule } from '@handsontable/angular';
import { ModalModule } from 'ngx-bootstrap/modal';
import { AppComponent } from './app.component';
import { Daterangepicker } from 'ng2-daterangepicker';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { ToastrModule } from 'ngx-toastr';

import { LostpasswordComponent } from './lostpassword/lostpassword.component';
import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { UnderconstructionComponent } from './underconstruction/underconstruction.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ResultsComponent } from './results/results.component';
import { DataService } from './data.service';
import { FilterdataService } from './services/filterdata.service';
import { ChannelTabContentComponent } from './channel-tab-content/channel-tab-content.component';

@NgModule({
  declarations: [
    AppComponent,
    LostpasswordComponent,
    LandingComponent,
    LoginComponent,
    UnderconstructionComponent,
    SidebarComponent,
    ResultsComponent,
    ChannelTabContentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    Daterangepicker,
    HttpClientModule,
    ReactiveFormsModule,
    ModalModule.forRoot(),
    TabsModule.forRoot(),
    HotTableModule.forRoot(),
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    AppRoutingModule
  ],
  providers: [DataService, FilterdataService],

  bootstrap: [AppComponent]
})
export class AppModule {}
