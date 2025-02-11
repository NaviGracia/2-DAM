import { Component, OnInit } from '@angular/core';
import { PokemonService } from '../services/pokemon.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { WishlistService } from '../services/wishlist.service';

@Component({
  selector: 'app-pokemon',
  templateUrl: './pokemon.component.html',
  styleUrls: ['./pokemon.component.css'],
  imports: [CommonModule, RouterModule],
})
export class PokemonComponent implements OnInit {
  sets: any[] = [];

  constructor(private pokemonService: PokemonService, private wishlistService: WishlistService) {}

  ngOnInit(): void {
    this.pokemonService.getPokemonSets().subscribe((data) => {
      this.sets = data;
    });
  }

  addToWishlist(item: any): void {
    this.wishlistService.addToWishlist(item);
  }
  
}
