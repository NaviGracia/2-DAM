import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HousingService } from '../housing.service';
import { HousingLocation } from '../housinglocation';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-housing-location',
  templateUrl: './edit-housing-location.component.html',
  styleUrls: ['./edit-housing-location.component.css'],
  imports: [
    FormsModule,
  ],
})
export class EditHousingLocationComponent implements OnInit {
  housingLocation: HousingLocation = {
    id: 0,
    name: '',
    city: '',
    state: '',
    photo: '',
    availableUnits: 0,
    wifi: false,
    laundry:false,
  };
  
  selectedFile: File | null = null;

  constructor(
    private housingService: HousingService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    const location = this.housingService
      .getAllHousingLocations()
      .find((loc) => loc.id === id);
  
    if (location) {
      this.housingLocation = location; // Solo asigna si existe un valor válido
    } else {
      alert('HousingLocation no encontrado.');
      this.router.navigate(['/']); // Redirige si no se encuentra
    }
  }
  

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      const reader = new FileReader();
      reader.onload = () => {
        if (this.housingLocation) {
          this.housingLocation.photo = reader.result as string;
        }
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }

  submitForm() {
    if (this.housingLocation) {
      this.housingService.updateHousingLocation(this.housingLocation);
      alert('Propiedad actualizada exitosamente');
      this.router.navigate(['/']); // Redirige a la página principal
    }
  }
}
