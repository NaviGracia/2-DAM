import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MagicSetComponent } from './magic-set.component';

describe('MagicSetComponent', () => {
  let component: MagicSetComponent;
  let fixture: ComponentFixture<MagicSetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MagicSetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MagicSetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
