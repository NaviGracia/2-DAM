import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  private favorites: any[] = [];

  constructor() {}

  addToFavorites(item: any) {
    if (!this.favorites.some(fav => fav.id === item.id)) {
      this.favorites.push(item);
    }
  }

  removeFromFavorites(item: any) {
    this.favorites = this.favorites.filter(fav => fav.id !== item.id);
  }

  getFavorites() {
    return this.favorites;
  }
}
