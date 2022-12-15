import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PermisionEditComponent } from './permision-edit.component';

describe('PermisionEditComponent', () => {
  let component: PermisionEditComponent;
  let fixture: ComponentFixture<PermisionEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PermisionEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PermisionEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
