import { Component, Input, OnInit } from '@angular/core';
import { TcgdexService } from '../services/tcgdex.service';
import { TCGSet } from '../models/set.model';

@Component({
  selector: 'app-set-list',
  templateUrl: './set-list.component.html',
  styleUrls: ['./set-list.component.css']
})
export class SetListComponent implements OnInit {
  @Input() tcgName: string = 'pokemon'; // Valor por defecto
  sets: TCGSet[] = []; // Ahora usamos la interfaz en lugar de any

  constructor(private tcgdexService: TcgdexService) {}

  ngOnInit(): void {
    this.tcgdexService.getSets(this.tcgName).subscribe((data: TCGSet[]) => {
      this.sets = data.filter(set => new Date(set.releaseDate).getFullYear() === 2024);
    });
  }
}
