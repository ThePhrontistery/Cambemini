import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KanbaSelectComponent } from './kanba-select.component';

describe('KanbaSelectComponent', () => {
  let component: KanbaSelectComponent;
  let fixture: ComponentFixture<KanbaSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KanbaSelectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KanbaSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
