import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  private wishlist: any[] = [];

  addToWishlist(item: any): void {
    if (!this.wishlist.some(fav => fav.id === item.id)) {
      this.wishlist.push(item);
    }
  }

  getWishlist(): any[] {
    return this.wishlist;
  }

  removeFromWishlist(item: any): void {
    this.wishlist = this.wishlist.filter(fav => fav.id !== item.id);
  }
}
