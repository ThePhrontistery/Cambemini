import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog } from '@angular/material/dialog';
import { KanbasService } from '../kanbas.service';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA, Component } from '@angular/core';

import { NoteComponent } from './note.component';
import { of } from 'rxjs';
import { KANBAS_DATA_LIST } from '../model/mock-kanbas-list';
import { LaneComponent } from '../lane/lane.component';
import { Notes } from '../model/Notes';
import { Lane } from '../model/Lane';
import { isNgTemplate } from '@angular/compiler';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

const MatDialogMock = {
  open() {
      return {
          afterClosed: () => of(true)
      };
  }
};

describe('None', () => {
  let component: NoteComponent;
  let fixture: ComponentFixture<NoteComponent>;
  let kanbasService: KanbasService;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
    ],
      declarations: [ 
        NoteComponent
       ],

      providers: [
        KanbasService,
        { provide: MatDialog, useValue: MatDialogMock },
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteComponent);
    component = fixture.componentInstance;

    component.item = {
      id: 1,
      title: 'Cloud design',
      description: 'Design of our cloud-based backend',
      laneId: 1,
    }
    component.taskLane = {
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
    }
    component.index = 0;
    component.indexY = 0;

    fixture.detectChanges();

    kanbasService = TestBed.inject(KanbasService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  })

  it('should remove', () => {
    let checkCode = component.remove();
    
    expect(checkCode).toBeTrue();
  });

  it('should edit', () => {
    let checkCode = component.edit();

    expect(checkCode).toBeTrue();
  });
});
