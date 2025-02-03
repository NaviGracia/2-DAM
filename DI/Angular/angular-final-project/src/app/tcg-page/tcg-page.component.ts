import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TcgdexService } from '../services/tcgdex.service';
import { TCGSet } from '../models/set.model';
import { CommonModule, TitleCasePipe } from '@angular/common';

@Component({
  selector: 'app-tcg-page',
  standalone: true,
  imports: [CommonModule, TitleCasePipe],
  templateUrl: './tcg-page.component.html',
  styleUrls: ['./tcg-page.component.css']
})
export class TcgPageComponent implements OnInit {
  tcgName: string = '';
  sets: TCGSet[] = [];

  constructor(private route: ActivatedRoute, private tcgdexService: TcgdexService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.tcgName = params.get('name') || 'pokemon'; // Valor por defecto
      this.loadSets();
    });
  }

  loadSets(): void {
    this.tcgdexService.getSets(this.tcgName).subscribe((data: TCGSet[]) => {
      this.sets = data;
    });
  }
}
