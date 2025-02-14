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
        mtgSets.sets
          .map(set => {
            const scryfallSet = scryfallSets.data.find(sSet => sSet.code.toLowerCase() === set.code.toLowerCase());
            return {
              id: set.id,
              name: set.name,
              releaseDate: set.releaseDate,
              code: set.code,
              logo: scryfallSet ? scryfallSet.icon_svg_uri : 'assets/img/magic_default_logo.png'
            };
          })
          .filter(set => set.releaseDate)
          .sort((a, b) => new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime()) 
      )
    );
}


getMagicSetCards(setCode: string): Observable<any[]> {
  const mtgCardsUrl = `https://api.magicthegathering.io/v1/cards?set=${setCode}`;
  const scryfallCardsUrl = `https://api.scryfall.com/cards/search?q=set%3A${setCode}`;

  return forkJoin({
    mtgCards: this.http.get<{ cards: any[] }>(mtgCardsUrl),
    scryfallCards: this.http.get<{ data: any[] }>(scryfallCardsUrl)
  }).pipe(
    map(({ mtgCards, scryfallCards }) => {
      console.log('Scryfall Cards:', scryfallCards.data); // 👈 Verifica si Scryfall devuelve datos

      return mtgCards.cards.map(card => {
        const scryfallCard = scryfallCards.data.find(sc => sc.name.toLowerCase() === card.name.toLowerCase());
        
        console.log('Carta encontrada:', card.name, ' - Imagen:', scryfallCard?.image_uris?.normal); // 👈 Verifica si se asigna imagen
        
        return {
          id: card.id,
          name: card.name,
          type: card.type,
          rarity: card.rarity,
          manaCost: card.manaCost,
          set: card.set,
          imageUrl: scryfallCard?.image_uris?.normal || card.imageUrl || 'assets/img/magic_default_card.png'
        };
      });
    })
  );
}



}
