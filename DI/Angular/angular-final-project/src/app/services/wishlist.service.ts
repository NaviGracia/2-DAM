import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class WishlistService {
  private wishlist: string[] = [];

  constructor() {
    const savedWishlist = localStorage.getItem('wishlist');
    this.wishlist = savedWishlist ? JSON.parse(savedWishlist) : [];
  }

  addToWishlist(item: string) {
    if (!this.wishlist.includes(item)) {
      this.wishlist.push(item);
      localStorage.setItem('wishlist', JSON.stringify(this.wishlist));
    }
  }

  removeFromWishlist(item: string) {
    this.wishlist = this.wishlist.filter(card => card !== item);
    localStorage.setItem('wishlist', JSON.stringify(this.wishlist));
  }

  getWishlist(): string[] {
    return this.wishlist;
  }

  isInWishlist(item: string): boolean {
    return this.wishlist.includes(item);
  }
}
