import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SetListComponent } from "./set-list/set-list.component";
import { HomeComponent } from "./home/home.component";
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, HomeComponent], // Importa RouterModule para que <router-outlet> y routerLink funcionen
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
}) 

export class AppComponent {
  title = 'angular-final-project';
}
