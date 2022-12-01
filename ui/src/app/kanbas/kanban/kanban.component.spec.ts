import { Lane } from '../model/Lane';
import { Task } from '../model/Notes';
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
    component.lanes = [{
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
    }];
    fixture.detectChanges();
    console.log('lanes', component.lanes);
    kanbasService = TestBed.inject(KanbasService);
    httpMock = TestBed.inject(HttpTestingController);
    // spyOn(kanbasService, 'getBooksFromCart').and.callFake(() => listBook);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should add', () => {
    let addResult = component.add();
    expect(addResult).toBeTrue();
  });
  
  it('should emitDeleteCard', () => {
    let task: Task = new Task();
    task.id = 1;
    task.title = 'UI implementation';
    task.description = 'Implementation of our Angular 14 Kanva UI';
    task.laneId = 1;
    
    component.lanes[0].items = [task];
    console.log(component.lanes[0].items.length);
    expect(component.lanes[0].items.length).toEqual(1);
    kanbasService.emitDeleteCard.emit({ KanbaIndex: 0, Itemindex: 0 });
    expect(component.lanes[0].items.length).toEqual(0);
  });

  it('should emitAddCard', () => {
    component.lanes[0].items = [];
    let task: Task = new Task();
    task.title = 'UI implementation';
    task.description = 'Implementation of our Angular 14 Kanva UI';
    task.laneId = 1;
    kanbasService.emitAddCard.emit(task);
    expect(component.lanes[0].items.length).toEqual(1);
  });

  it('should emitAddCard', () => {
    let task: Task = new Task();
    task.id = 1;
    task.title = 'UI implementation';
    task.description = 'Implementation of our Angular 14 Kanva UI';
    task.laneId = 1;
    component.lanes[0].items = [task];

    task.title = 'Otro title';
    kanbasService.emitAddCard.emit(task);
    expect(component.lanes[0].items[0].title).toEqual('Otro title');
  });
 
  it('should  edit on emitAddKanba', () => {
    let lane: Lane = {
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
    component.lanes= [lane]
    lane.title = "To do change"
  
    kanbasService.emitAddKanba.emit(lane);
    expect(component.lanes[0].title).toEqual('To do change');
  });

  it('should  add on emitAddKanba', () => {
    let lane: Lane = {
      id: 5,
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
          laneId: 5,
        },
      ],
    }
    lane.id = null;
    component.lanes= []
   
    expect(component.lanes.length).toEqual(0);
    kanbasService.emitAddKanba.emit(lane);
    expect(component.lanes.length).toEqual(1);
  });
  
  it('should  emitRemoveLane', () => {
    let lane: Lane = {
      id: 7,
      title: 'To do',
      color: '',
      order: 0,
      items: [
        {
          id: 1,
          title: 'Cloud design',
          description: 'Design of our cloud-based backend',
          laneId: 7,
        },
        {
          id: 2,
          title: 'Client tasks usability',
          description:
            'Think and design how clients will interact with notes at the same time',
          laneId: 7,
        },
      ],
    }
    
    component.lanes= [lane]
   
    expect(component.lanes.length).toEqual(1);
    kanbasService.emitRemoveLane.emit(lane);
    expect(component.lanes.length).toEqual(0);
  });


});
