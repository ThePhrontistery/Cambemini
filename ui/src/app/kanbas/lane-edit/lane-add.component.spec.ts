import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { of } from 'rxjs';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LaneEditComponent } from './lane-edit.component';
import { KanbasService } from '../kanbas.service';
import { NO_ERRORS_SCHEMA, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

// value from new
const data = {
  entitie: null,
};

const MatDialogRefMock = {
  close() {
    return {
      close: () => {},
    };
  },
};

describe('LaneAddComponent', () => {
  let component: LaneEditComponent;
  let fixture: ComponentFixture<LaneEditComponent>;
  let kanbasService: KanbasService;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        // HttpClientTestingModule,
      ],
      declarations: [LaneEditComponent],
      providers: [
        KanbasService,
        { provide: MatDialogRef, useValue: MatDialogRefMock },
        { provide: MAT_DIALOG_DATA, useValue: data },
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LaneEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    kanbasService = TestBed.inject(KanbasService);
    // spyOn(kanbasService, 'getBooksFromCart').and.callFake(() => listBook);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should onClose', () => {
    let onCLose = component.onClose();
    expect(onCLose).toBeTrue();
  });

  it('should onSave', () => {
    let onSave = component.onSave();
    expect(onSave).toBeTrue();
  });
});
