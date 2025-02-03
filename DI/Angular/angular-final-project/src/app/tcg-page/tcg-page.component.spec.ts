import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TcgPageComponent } from './tcg-page.component';

describe('TcgPageComponent', () => {
  let component: TcgPageComponent;
  let fixture: ComponentFixture<TcgPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TcgPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TcgPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
