import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SetListComponent } from "./set-list/set-list.component";
import { HomeComponent } from "./home/home.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, SetListComponent, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-final-project';
}
