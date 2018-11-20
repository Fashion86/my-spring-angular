import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { UnderconstructionComponent } from './underconstruction/underconstruction.component';
import { ResultsComponent } from './results/results.component';

const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'login', component: LoginComponent },
    { path: 'landing', component: LandingComponent },
    { path: 'underconstruction', component: UnderconstructionComponent },
    { path: 'results', component: ResultsComponent },
    { path: '**',  redirectTo: '/login', pathMatch: 'full'}
  // { path: '**',  redirectTo: '/reports', pathMatch: 'full', canActivate: [AuthenticationGuard]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
