import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MagicService {
  private apiUrl = 'https://api.magicthegathering.io/v1/sets'; // URL de la API

  constructor(private http: HttpClient) {}

  getMagicSets(): Observable<any[]> {
    return this.http.get<{ sets: any[] }>(this.apiUrl).pipe(
      map(response =>
        response.sets.sort((a, b) => 
          new Date(b.releaseDate || '1970-01-01').getTime() - new Date(a.releaseDate || '1970-01-01').getTime()
        )
      )
    );
  }
}
