import { Component } from '@angular/core';
import { WishlistService } from '../../services/wishlist.service'; 
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-wishlist',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css'],
})
export class WishlistComponent {
  wishlist: string[] = [];

  constructor(private wishlistService: WishlistService) {}

  ngOnInit() {
    this.loadWishlist();
  }

  loadWishlist() {
    this.wishlist = this.wishlistService.getWishlist();
  }

  removeFromWishlist(item: string) {
    this.wishlistService.removeFromWishlist(item);
    this.loadWishlist(); // Actualizar la lista
  }
}
