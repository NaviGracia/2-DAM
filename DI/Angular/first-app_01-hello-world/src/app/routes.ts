import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {DetailsComponent} from './details/details.component';
import { AddHousingLocationComponent } from './add-housing-location/add-housing-location.component';
import { EditHousingLocationComponent } from './edit-housing-location/edit-housing-location.component';


const routeConfig: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Home page',
  },
  {
    path: 'details/:id',
    component: DetailsComponent,
    title: 'Home details',
  },
  { 
    path: 'add-housing-location', 
    component: AddHousingLocationComponent,
    title: 'Add Housing Location',
  },
  {
    path: 'edit-housing-location/:id',
    component: EditHousingLocationComponent,
  }
  
];

export default routeConfig;