import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {
  private apiUrl = 'https://api.pokemontcg.io/v2/sets'; 

  constructor(private http: HttpClient) {}

  getPokemonSets(): Observable<any[]> {
    return this.http.get<{ data: any[] }>(this.apiUrl).pipe(
      map(response => 
        response.data.sort((a, b) => 
          new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime()
        )
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
