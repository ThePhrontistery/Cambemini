import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Kanban } from '../model/Kanban';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';

import { KANBAS_DATA_LIST, LANE_DATA_LIST } from '../model/mock-kanbas-list';
import { MatDialog } from '@angular/material/dialog';
import { of } from 'rxjs';
import { KanbasService } from '../kanbas.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { KanbasListComponent } from './kanbas-list.component';

const MatDialogMock = {
  open() {
    return {
      afterClosed: () => of(true),
    };
  },
};

const RouterMock = {
  navigate(item:[]) {
    return {
      afterClosed: () => of(true),
    };
  },
};

const kanba: Kanban = {
  id: 1,
  title: 'Escape',
  description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
  select: true,
  userKanbanPermission:[
    {id:1, users:{id:1,email:'mercedes@escape.com', online:false}, permission:{id:1,rol:"Owner"}},
    {id:2, users:{id:2,email:'raul@escape.com', online:false}, permission:{id:2,rol:"Editor"}}
  ],
  
  swimlanes:LANE_DATA_LIST
};

describe('KanbasComponent', () => {
  let component: KanbasListComponent;
  let fixture: ComponentFixture<KanbasListComponent>;
  let kanbasService: KanbasService;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
         HttpClientTestingModule,
      ],
      declarations: [KanbasListComponent],

      providers: [
        KanbasService,
        { provide: MatDialog, useValue: MatDialogMock },
        { provide: Router, useValue: RouterMock },
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
    }).compileComponents();

    fixture = TestBed.createComponent(KanbasListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KanbasListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    kanbasService = TestBed.inject(KanbasService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should remove', () => {
   
   
    component.listKanbas = [
      {
        id: 1,
        title: 'Escape',
        description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
        select: true,
        userKanbanPermission:[
          {id:1, users:{id:1,email:'mercedes@escape.com', online:false}, permission:{id:1,rol:"Owner"}},
          {id:2, users:{id:2,email:'raul@escape.com', online:false}, permission:{id:2,rol:"Editor"}}
        ],
        
        swimlanes:LANE_DATA_LIST
      },
    ];
    // expect(component.listKanbas.length).toEqual(1);
    
    /*
    * El método callFake hace que, al llamar a esa función, realice una función mockeada pasada por
    * parámetro, esto hace que ejecute esa funcion mock en lugar de la definida en el servicio.
    */
    let spyOnRemove = spyOn(kanbasService,"removeKanban").and.callFake(() => {return of()});
    component.remove(component.listKanbas[0]);
   
    expect(spyOnRemove).toHaveBeenCalled();
    
  });

  it('should newKanba', () => {
    let getNewKanba = component.newKanba();
    expect(getNewKanba).toBeTrue();
  });

  it('should editKanba', () => {
    let edit = component.edit(kanba);
    expect(edit).toBeTrue();
  });
  
  it('should go', () => {
    
    // let spyOnGo = spyOn(component,'go').withArgs(kanba).and.callFake(()=>null);
    let goResult = component.go(kanba); 
    expect(goResult).toBeTruthy();
  });
});
