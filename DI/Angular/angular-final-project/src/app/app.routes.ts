import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { TcgPageComponent } from './tcg-page/tcg-page.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'tcg/:name', component: TcgPageComponent },
  { path: '**', redirectTo: '' }
];
