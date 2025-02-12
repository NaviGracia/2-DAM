import { Component, OnInit } from '@angular/core';
import { MagicService } from '../services/magic.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { WishlistService } from '../services/wishlist.service';

@Component({
  selector: 'app-magic',
  templateUrl: './mtg.component.html',
  styleUrls: ['./mtg.component.css'],
  imports: [CommonModule, RouterModule],
})
export class MagicComponent implements OnInit {
  sets: any[] = [];

  constructor(private magicService: MagicService, private wishlistService: WishlistService) {}

  ngOnInit(): void {
    this.magicService.getMagicSets().subscribe((data) => {
      this.sets = data; 
    });
  }

  addToFavorites(set: any){
    this.wishlistService.addToFavorites(set);
  }
}
