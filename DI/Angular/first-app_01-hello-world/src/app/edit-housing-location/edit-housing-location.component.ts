import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HousingService } from '../housing.service';
import { HousingLocation } from '../housinglocation';

@Component({
  selector: 'app-edit-housing-location',
  templateUrl: './edit-housing-location.component.html',
  styleUrls: ['./edit-housing-location.component.css']
})
export class EditHousingLocationComponent implements OnInit {
  housingLocation: HousingLocation | null = null;
  selectedFile: File | null = null;

  constructor(
    private housingService: HousingService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.housingLocation = this.housingService
      .getAllHousingLocations()
      .find((location) => location.id === id) || null;
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
