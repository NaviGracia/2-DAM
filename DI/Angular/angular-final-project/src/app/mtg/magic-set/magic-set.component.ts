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
  setId: string = '';
  cards: any[] = [];

  constructor(private route: ActivatedRoute, private magicService: MagicService, private wishlistService: WishlistService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.setId = params.get('id') || '';
      if (this.setId) {
        this.magicService.getMagicSetCards(this.setId).subscribe(cards => {
          this.cards = cards;
          console.log('Cartas después de asignarlas:', this.cards); // 👈 Verifica si las imágenes llegan aquí
        });
      }
    });
  }

  addToWishlist(item: any): void {
    this.wishlistService.addToFavorites(item);
  }
}
