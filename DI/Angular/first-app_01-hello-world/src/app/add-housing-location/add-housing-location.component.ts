import { Component } from '@angular/core';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HousingService } from '../housing.service';
import { HousingLocation } from '../housinglocation';

@Component({
  standalone: true,
  selector: 'app-add-housing-location',
  templateUrl: './add-housing-location.html',
  styleUrls: ['./add-housing-location.css'],
  imports: [NgIf, FormsModule],
})
export class AddHousingLocationComponent {
  newHousingLocation: Partial<HousingLocation> = {};
  selectedFile: File | null = null; // Propiedad para almacenar el archivo

  constructor(private housingService: HousingService, private router: Router) {}

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
  
      // Convertir el archivo a Base64
      const reader = new FileReader();
      reader.onload = () => {
        this.newHousingLocation.photo = reader.result as string; // Guardar la imagen como Base64
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }
  
  submitForm() {
    if (this.selectedFile) {
      console.log('Archivo procesado como Base64:', this.newHousingLocation.photo);
    }
    
    const allLocations = this.housingService.getAllHousingLocations();
    const newId = allLocations.length;
    this.newHousingLocation.id = newId;
    console.log('Nuevo ID:' + newId);

    this.housingService.addHousingLocation(this.newHousingLocation as HousingLocation);
    alert('Propiedad agregada exitosamente');
    this.router.navigate(['/']);
  }
  
}
