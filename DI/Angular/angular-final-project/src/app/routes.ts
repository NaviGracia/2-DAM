import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import { SetListComponent } from './set-list/set-list.component';


const routeConfig: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Página Inicial',
  },
  {
    path: 'set-list',
    component: SetListComponent,
    title: 'Lista de Sets',
  }
  
];

export default routeConfig;