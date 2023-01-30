import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog } from '@angular/material/dialog';
import { KanbasService } from '../../../kanbas.service';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA, Component } from '@angular/core';

import { NoteComponent } from './note.component';
import { empty, Observable, of } from 'rxjs';
import { KANBAS_DATA_LIST } from '../../../model/mock-kanbas-list';
import { LaneComponent } from '../lane.component';
import { Notes } from '../../../model/Notes';
import { Lane } from '../../../model/Lane';
import { isNgTemplate } from '@angular/compiler';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

const MatDialogMock = {
  open() {
      return {
          afterClosed: () => of(true)
      };
  }
};

describe('Note', () => {
  let component: NoteComponent;
  let fixture: ComponentFixture<NoteComponent>;
  let kanbasService: KanbasService;
  let httpMock: HttpTestingController;
  let spy;

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
      content: 'Cloud design',
      order: 1,
    },
    component.taskLane = {
      id: 1,
      title: 'To do',
      order: 1,
      notes: [
        {
          id: 1,
          content: 'Cloud design',
          order: 1,
        },
        {
          id: 2,        
          content:
            'Think and design how clients will interact with notes at the same time',
          order: 2,
        },
      ],
    },
    component.index = 0;
    component.indexY = 0;
    kanbasService = TestBed.inject(KanbasService);
    httpMock = TestBed.inject(HttpTestingController);

    spy = spyOn(kanbasService, 'removeNote').and.callFake(() => of(true));
    fixture.detectChanges();
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
