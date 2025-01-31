import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TCGSet } from '../models/set.model';

@Injectable({
  providedIn: 'root'
})
export class TcgdexService {
  private API_URLS: { [key: string]: string } = {
    pokemon: 'https://api.tcgdex.dev/v2/es/sets',
    yugioh: 'https://api.yugioh.dev/v1/sets',  // Ejemplo ficticio
    magic: 'https://api.magicthegathering.io/v1/sets' // Ejemplo ficticio
  };

  constructor(private http: HttpClient) {}

  getSets(tcg: string): Observable<TCGSet[]> {
    const apiUrl = this.API_URLS[tcg] || this.API_URLS['pokemon'];
    return this.http.get<TCGSet[]>(apiUrl);
  }
}
