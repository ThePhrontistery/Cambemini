import { LANE_DATA_LIST, KANBAS_DATA_LIST } from './../model/mock-kanbas-list';
import { Lane } from '../model/Lane';
import { Notes } from '../model/Notes';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { KanbasService } from '../kanbas.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { KanbanComponent } from './kanban.component';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

const MatDialogMock = {
  open() {
    return {
      afterClosed: () => of(true),
    };
  },
};

const ActivatedRouteMock = {
  snapshot: {
    params: {
      code: 'mural-0002',
    },
  },
};

describe('KanbaComponent', () => {
  let component: KanbanComponent;
  let fixture: ComponentFixture<KanbanComponent>;
  let kanbasService: KanbasService;
  let httpMock: HttpTestingController;
  let spy;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      declarations: [KanbanComponent],
      providers: [
        KanbasService,
        { provide: MatDialog, useValue: MatDialogMock },
        { provide: ActivatedRoute, useValue: ActivatedRouteMock },
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KanbanComponent);
    component = fixture.componentInstance;
    kanbasService = TestBed.inject(KanbasService);
    httpMock = TestBed.inject(HttpTestingController);
    
    spy=spyOn(kanbasService, 'getKanbans').and.callFake(() => of(KANBAS_DATA_LIST));
    
    fixture.detectChanges();
    console.log('lanes', component.lanes);
  
     
  });

  it('should create', () => {
    expect(spy).toHaveBeenCalled();
    expect(component).toBeTruthy();
  });

  it('should add a swimlane', () => {
    let addResult = component.addSwimlane();
    expect(addResult).toBeTrue();
  });
  
  
  it('should  emitRemoveLane', () => {
    
    let lane:Lane = {
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
    };
    component.lanes= LANE_DATA_LIST;
   
    expect(component.lanes.length).toEqual(4);
    kanbasService.emitRemoveLane.emit(lane);
    expect(component.lanes.length).toEqual(3);
  });


});
