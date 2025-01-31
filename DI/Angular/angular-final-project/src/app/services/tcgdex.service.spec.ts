import { TestBed } from '@angular/core/testing';

import { TcgdexService } from './tcgdex.service';

describe('TcgdexService', () => {
  let service: TcgdexService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TcgdexService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
