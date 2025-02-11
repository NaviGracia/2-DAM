import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class PokemonService {
   // URL de la API
  private apiUrl = 'https://api.pokemontcg.io/v2/sets';

  constructor(private http: HttpClient) {}

  // Ordenar de más reciente a más antiguo
  getPokemonSets(): Observable<any[]> {
    return this.http.get<{ sets: any[] }>(this.apiUrl).pipe(
      map(response => 
        response.sets.sort((a, b) => 
          new Date(b.releaseDate || '1970-01-01').getTime() - new Date(a.releaseDate || '1970-01-01').getTime())
      )
    );      
  }

  getCardsBySet(setId: string): Observable<any[]> {
    const url = `https://api.pokemontcg.io/v2/cards?q=set.id:${setId}`;
    return this.http.get<{ data: any[] }>(url).pipe(
      map(response => response.data) 
    );
  }
}

