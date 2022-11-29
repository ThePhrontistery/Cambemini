import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { of } from 'rxjs';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LaneEditComponent } from './lane-edit.component';
import { KanbasService } from '../kanbas.service';
import { NO_ERRORS_SCHEMA, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

// value from new
const data = {
  entitie: {
    id: 1,
    title: 'To do',
    color: '',
    order: 0,
    items: [
      {
        id: 1,
        title: 'Cloud design',
        description: 'Design of our cloud-based backend',
        laneId: 1,
      },
      {
        id: 2,
        title: 'Client tasks usability',
        description:
          'Think and design how clients will interact with notes at the same time',
        laneId: 1,
      },
    ],
  },
};

const MatDialogRefMock = {
  close() {
    return {
      close: () => {},
    };
  },
};

describe('LaneEditComponent1', () => {
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
