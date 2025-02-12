import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { forkJoin, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MagicService {
  private mtgApiUrl = 'https://api.magicthegathering.io/v1/sets'; // Datos del set
  private scryfallApiUrl = 'https://api.scryfall.com/sets'; // Imágenes del set

  constructor(private http: HttpClient) {}

  getMagicSets(): Observable<any[]> {
    return forkJoin({
      mtgSets: this.http.get<{ sets: any[] }>(this.mtgApiUrl),
      scryfallSets: this.http.get<{ data: any[] }>(this.scryfallApiUrl)
    }).pipe(
      map(({ mtgSets, scryfallSets }) =>
        mtgSets.sets.map(set => {
          const scryfallSet = scryfallSets.data.find(sSet => sSet.code.toLowerCase() === set.code.toLowerCase());
          return {
            id: set.id,
            name: set.name,
            releaseDate: set.releaseDate,
            code: set.code,
            logo: scryfallSet ? scryfallSet.icon_svg_uri : 'assets/img/magic_default_logo.png' 
          };
        })
      )
    );
  }

  getCardsBySet(setCode: string): Observable<any[]> {
    const url = `https://api.magicthegathering.io/v1/cards?set=${setCode}`;
    return this.http.get<{ cards: any[] }>(url).pipe(
      map(response => response.cards) 
    );
  }
}
