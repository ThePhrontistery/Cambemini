import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KanbasEditComponent } from './kanbas-edit.component';

describe('KanbasEditComponent', () => {
  let component: KanbasEditComponent;
  let fixture: ComponentFixture<KanbasEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KanbasEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KanbasEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
