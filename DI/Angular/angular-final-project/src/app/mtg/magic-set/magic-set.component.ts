import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {MagicService} from '../../services/magic.service';
import { CommonModule } from '@angular/common';
import { WishlistService } from '../../services/wishlist.service';

@Component({
  selector: 'app-magic-set',
  templateUrl: './magic-set.component.html',
  styleUrls: ['./magic-set.component.css'],
  imports: [CommonModule],
})
export class MagicSetComponent implements OnInit {
  setCode: string = '';
  cards: any[] = [];

  constructor(private route: ActivatedRoute, private magicService: MagicService, private wishlistService: WishlistService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.setCode = params.get('setCode') || '';
      this.loadCards();
    });
  }

  loadCards(): void {
    this.magicService.getCardsBySet(this.setCode).subscribe((data) => {
      this.cards = data;
    });
  }

  addToWishlist(item: any): void {
    this.wishlistService.addToFavorites(item);
  }
}
