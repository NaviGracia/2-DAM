import { Component, OnInit } from '@angular/core';
import { WishlistService } from '../../services/wishlist.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css'],
  imports: [CommonModule]
})
export class WishlistComponent implements OnInit {
  wishlist: any[] = [];

  constructor(private wishlistService: WishlistService) {}

  ngOnInit() {
    this.wishlist = this.wishlistService.getFavorites();
  }

  removeFromWishlist(item: any) {
    this.wishlistService.removeFromFavorites(item);
    this.wishlist = this.wishlistService.getFavorites();
  }
}
