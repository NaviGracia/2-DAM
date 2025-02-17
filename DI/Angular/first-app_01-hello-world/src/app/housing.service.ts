import {Injectable} from '@angular/core';
import {HousingLocation} from './housinglocation';
@Injectable({
  providedIn: 'root',
})
export class HousingService {
  readonly baseUrl = 'https://angular.dev/assets/images/tutorials/common';
  protected housingLocationList: HousingLocation[] = [
    {
      id: 0,
      name: 'Acme Fresh Start Housing',
      city: 'Chicago',
      state: 'IL',
      photo: `${this.baseUrl}/bernard-hermant-CLKGGwIBTaY-unsplash.jpg`,
      availableUnits: 4,
      wifi: true,
      laundry: true,
    },
    {
      id: 1,
      name: 'A113 Transitional Housing',
      city: 'Santa Monica',
      state: 'CA',
      photo: `${this.baseUrl}/brandon-griggs-wR11KBaB86U-unsplash.jpg`,
      availableUnits: 0,
      wifi: false,
      laundry: true,
    },

  ];

  getAllHousingLocations(): HousingLocation[] {
    return this.housingLocationList;
  }

  getHousingLocationById(id: number): HousingLocation | undefined {
    return this.housingLocationList.find((housingLocation) => housingLocation.id === id);
  }
  
  submitApplication(firstName: string, lastName: string, email: string) {
    console.log(
      `Homes application received: firstName: ${firstName}, lastName: ${lastName}, email: ${email}.`,
    );
  }

  addHousingLocation(location: HousingLocation) {
    this.housingLocationList.push(location);
  }
  
  updateHousingLocation(updatedLocation: HousingLocation): void {
    const index = this.housingLocationList.findIndex(
      (location) => location.id === updatedLocation.id
    );
  
    if (index !== -1) {
      this.housingLocationList[index] = updatedLocation; // Actualiza la ubicación en la lista
    } else {
      console.error('No se encontró la ubicación con el id:', updatedLocation.id);
    }
  }
  
  deleteHousingLocation(id: number): void {
    const index = this.housingLocationList.findIndex((location) => location.id === id);
    if (index !== -1) {
      this.housingLocationList.splice(index, 1); // Elimina el elemento de la lista
    } else {
      console.error('No se encontró la ubicación con el id:', id);
    }
  }
  
  
}