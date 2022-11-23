import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { Kanba } from './../model/Kanbas';
import { KANBAS_DATA_LIST, LANE_DATA_LIST } from './../model/mock-kanbas-list';
import { MatDialog } from '@angular/material/dialog';
import { of } from 'rxjs';
import { KanbasService } from './../kanbas.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { KanbasComponent } from './kanbas.component';

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

const kanba: Kanba = {
  id: 1,
  title: 'Escape',
  description:
    'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',

  code: 'mural-0001',
  select: true,
  icon: 'settings_accesibility',
  users: [
    { email: 'fredy@test.com', initial: 'FHO', online: true },
    { initial: 'DAV', email: 'david@test.com', online: false },
  ],
  lanes: LANE_DATA_LIST,
};

fdescribe('KanbasComponent', () => {
  let component: KanbasComponent;
  let fixture: ComponentFixture<KanbasComponent>;
  let kanbasService: KanbasService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        // HttpClientTestingModule,
      ],
      declarations: [KanbasComponent],

      providers: [
        KanbasService,
        { provide: MatDialog, useValue: MatDialogMock },
        { provide: Router, useValue: RouterMock },
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
    }).compileComponents();

    fixture = TestBed.createComponent(KanbasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KanbasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    kanbasService = TestBed.inject(KanbasService);
    spyOn(kanbasService, 'getKanbas').and.callFake(() => of(KANBAS_DATA_LIST));
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should remove', () => {
    component.listKanbas = [
      {
        id: 20,
        title: 'Escape',
        description:
          'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',

        code: 'mural-0000020',
        select: true,
        icon: 'settings_accesibility',
        users: [
          { email: 'fredy@test.com', initial: 'FHO', online: true },
          { initial: 'DAV', email: 'david@test.com', online: false },
        ],
        lanes: LANE_DATA_LIST,
      },
    ];
    expect(component.listKanbas.length).toEqual(1);
    component.remove(0);
    expect(component.listKanbas.length).toEqual(0);
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

  
  it('should emitSaveKanba', () => {
    
    // let spyOnGo = spyOn(component,'go').withArgs(kanba).and.callFake(()=>null);
        let kanbaNew = new Kanba();
        kanbaNew.code = "cod-002"
        kanbaNew.title = "titulo"
        kanbaNew.description = "description"
        kanbaNew.lanes =[];
        kanbaNew.icon ='icon';
        kanbaNew.select = false;
      
      kanbasService.emitSaveKanba.emit(kanbaNew);
       expect(component.listKanbas.length).toBeGreaterThan(0);
  });

  it('should emitSaveKanba exist', () => {  
      kanbasService.emitSaveKanba.emit(kanba);
      expect(component.listKanbas.length).toBeGreaterThan(0);
  });




});
