import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PokemonComponent } from './pokemon/pokemon.component';
import { MagicComponent } from './mtg/mtg.component';
import { RegisterComponent } from './user/register/register.component';
import { LoginComponent } from './user/login/login.component';
import { WishlistComponent } from './user/wishlist/wishlist.component';
import { MagicSetComponent } from './mtg/magic-set/magic-set.component';
import { ProfileComponent } from './user/profile/profile.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'pokemon', component: PokemonComponent },
  { path: 'magic', component: MagicComponent },
  { path: 'magic/set/:setCode', component: MagicSetComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'wishlist', component: WishlistComponent },
  { path: 'profile', component: ProfileComponent },
];

