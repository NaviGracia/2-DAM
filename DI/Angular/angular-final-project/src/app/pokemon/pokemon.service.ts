import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {
  private apiUrl = 'https://api.tcgdex.net/v2/en/sets'; 

  constructor(private http: HttpClient) {}

  getPokemonSets(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      map(sets => sets.sort((a, b) => new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime())) // Ordenar de más reciente a más antiguo
    );
  }
}
