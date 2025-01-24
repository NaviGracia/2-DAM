import {Component, Input} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HousingLocation} from '../housinglocation';
import {RouterModule} from '@angular/router';
import { HousingService } from '../housing.service';

@Component({
  selector: 'app-housing-location',
  imports: [CommonModule, RouterModule],
  template: `
    <section class="listing">
      <img
        class="listing-photo"
        [src]="housingLocation.photo"
        alt="Exterior photo of {{ housingLocation.name }}"
        crossorigin
      />
      <a [routerLink]="['/edit-housing-location', housingLocation.id]">Edit</a>
      <button (click)="deleteLocation(housingLocation.id)">Delete</button>
      <h2 class="listing-heading">{{ housingLocation.name }}</h2>
      <p class="listing-location">{{ housingLocation.city }}, {{ housingLocation.state }}</p>
      <a [routerLink]="['/details', housingLocation.id]">Learn More</a>
    </section>
  `,
  styleUrls: ['./housing-location.component.css'],
})
export class HousingLocationComponent {
  @Input() housingLocation!: HousingLocation;

  housingLocations: HousingLocation[] = [];

  constructor(private housingService: HousingService) {}

  ngOnInit(): void {
    this.housingLocations = this.housingService.getAllHousingLocations();
  }

  deleteLocation(id: number): void {
    const confirmDelete = confirm('¿Estás seguro de que quieres eliminar esta ubicación?');
    if (confirmDelete) {
      this.housingService.deleteHousingLocation(id);
      this.housingLocations = this.housingService.getAllHousingLocations(); // Actualiza la lista
    }
  }
}