import { Task } from './../model/Task';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { KanbasService } from './../kanbas.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { KanbaListComponent } from './kanba-list.component';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

const MatDialogMock = {
  open() {
      return {
          afterClosed: () => of(true)
      };
  }
};

const ActivatedRouteMock = {
  snapshot:{
    params:{
      code:'mural-0002'
    }
  } 
};

fdescribe('KanbaListComponent', () => {
  let component: KanbaListComponent;
  let fixture: ComponentFixture<KanbaListComponent>;
  let kanbasService: KanbasService;
  
  
  beforeEach(async () => {
    await TestBed.configureTestingModule(
      {
        imports: [
          // HttpClientTestingModule,
        ],
        declarations: [ KanbaListComponent ],
        providers: [
          KanbasService,
           { provide: MatDialog, useValue: MatDialogMock },
           { provide: ActivatedRoute, useValue: ActivatedRouteMock },
       ],
       schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA]
      },
      
    )
    .compileComponents();
    
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KanbaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    console.log('lanes',component.lanes);    
    kanbasService = TestBed.inject(KanbasService);
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
    
     console.log(component.lanes[0].items.length);
     expect(component.lanes[0].items.length).toEqual(2);
     kanbasService.emitDeleteCard.emit({KanbaIndex:0,Itemindex:0})
     expect(component.lanes[0].items.length).toEqual(1);
  });
  
  xit('should emitAddCard', () => {
     component.lanes[0].items = [];    
    //  expect(component.lanes[0].items.length).toEqual(0);

     let task:Task = {
      "id": 4,
      "title": "UI implementation",
      "description": "Implementation of our Angular 14 Kanva UI",
      "laneId": 1
     }
    
    spyOn(kanbasService.emitAddCard, 'emit').and.callFake(()=>task);
    expect(component.lanes[0].items.length).toBeGreaterThan(0);  
        
  });

});
