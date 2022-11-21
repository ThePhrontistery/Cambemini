import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KanbasComponent } from './kanbas.component';

describe('KanbasComponent', () => {
  let component: KanbasComponent;
  let fixture: ComponentFixture<KanbasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KanbasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KanbasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
