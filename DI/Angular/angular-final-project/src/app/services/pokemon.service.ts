import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { PokemonSet } from '../models/set.model';

@Injectable({
  providedIn: 'root'
})

export class PokemonService {
   // URL de la API
  private apiUrl = 'https://api.pokemontcg.io/v2/sets';

  constructor(private http: HttpClient) {}

  // Ordenar de más reciente a más antiguo
  getPokemonSets(): Observable<any[]> {
    return this.http.get<{ data: PokemonSet[] }>(this.apiUrl).pipe(
      map(response => 
        response.data.sort((a, b) => 
          new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime()
        )
      )
    );      
  }
}

