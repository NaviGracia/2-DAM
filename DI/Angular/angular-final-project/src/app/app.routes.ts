import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PokemonComponent } from './pokemon/pokemon.component';
import { MagicComponent } from './mtg/mtg.component';
import { RegisterComponent } from './user/register/register.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'pokemon', component: PokemonComponent },
  { path: 'magic', component: MagicComponent },
  { path: 'register', component: RegisterComponent },
];

