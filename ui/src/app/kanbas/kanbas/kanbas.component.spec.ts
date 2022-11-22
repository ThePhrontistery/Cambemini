import { KANBAS_DATA_LIST } from './../model/mock-kanbas-list';
import { MatDialog } from '@angular/material/dialog';
import { of } from 'rxjs';
import { KanbasService } from './../kanbas.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA, Component } from '@angular/core';
import { KanbasComponent } from './kanbas.component';


const MatDialogMock = {
  open() {
      return {
          afterClosed: () => of(true)
      };
  }
};

fdescribe('KanbasComponent', () => {
  let component: KanbasComponent;
  let fixture: ComponentFixture<KanbasComponent>;
  let kanbasService:KanbasService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        // HttpClientTestingModule,
    ],
      declarations: [ KanbasComponent ],

      providers: [
        KanbasService,
        { provide: MatDialog, useValue: MatDialogMock },
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KanbasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KanbasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    kanbasService = TestBed.inject(KanbasService);
    spyOn(kanbasService, 'getKanbas').and.callFake(()=>of(KANBAS_DATA_LIST));
    
});

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
  it('should remove', () => {
    expect(component.listKanbas.length).toEqual(2);
    component.remove(1);    
    expect(component.listKanbas.length).toEqual(1);
  });
  
  it('should newKanba', () => {   
   let getNewKanba = component.newKanba();    
    expect(getNewKanba).toBeTrue();
  });


});
