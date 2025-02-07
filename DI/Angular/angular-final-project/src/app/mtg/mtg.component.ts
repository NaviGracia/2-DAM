import { Component, OnInit } from '@angular/core';
import { MagicService } from './magic.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-magic',
  templateUrl: './mtg.component.html',
  styleUrls: ['./mtg.component.css'],
  imports: [CommonModule],
})
export class MagicComponent implements OnInit {
  sets: any[] = [];

  constructor(private magicService: MagicService) {}

  ngOnInit(): void {
    this.magicService.getMagicSets().subscribe((data) => {
      this.sets = data; // Almacena los sets en el array
    });
  }
}
