import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PokemonService } from '../../services/pokemon.service';
import { CommonModule } from '@angular/common';
import { WishlistService } from '../../services/wishlist.service';


@Component({
  selector: 'app-pokemon-set',
  templateUrl: './pokemon-set.component.html',
  styleUrls: ['./pokemon-set.component.css'],
  imports: [CommonModule],
})
export class PokemonSetComponent implements OnInit {
  setId: string = '';
  cards: any[] = [];

  constructor(private route: ActivatedRoute, private pokemonService: PokemonService, private wishlistService: WishlistService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.setId = params.get('setId') || '';
      this.loadCards();
    });
  }

  loadCards(): void {
    this.pokemonService.getCardsBySet(this.setId).subscribe((data) => {
      this.cards = data;
    });
  }

  addToWishlist(item: any): void {
    this.wishlistService.addToWishlist(item);
  }
  
}
