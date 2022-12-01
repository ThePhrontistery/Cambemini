import { LANE_DATA_LIST, KANBAS_DATA_LIST, LANE_DATA_LIST1 } from './../../kanbas/model/mock-kanbas-list';
import { Kanban } from '../../kanbas/model/Kanbas';
import { CoreModule } from './../core.module';
import { MatMenuModule } from '@angular/material/menu';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { KanbasService } from 'src/app/kanbas/kanbas.service';

import { KanbaSelectComponent } from './kanba-select.component';
import { BrowserDynamicTestingModule } from '@angular/platform-browser-dynamic/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('KanbaSelectComponent', () => {
  let component: KanbaSelectComponent;
  let fixture: ComponentFixture<KanbaSelectComponent>;
  let kanbasService: KanbasService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        CoreModule
      ],
      declarations: [KanbaSelectComponent],
      providers: [
        KanbasService,

     
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
    })
    .compileComponents();

    fixture = TestBed.createComponent(KanbaSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
  it('should selectKanba', () => {
    component.listKanbas = KANBAS_DATA_LIST;
    let  Kanba:Kanban = {
      id: 2,
      title: 'Site',
      description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
      code: 'mural-0002',
      select: false,
      icon: 'settings_accesibility',
      users: [
        { email: 'mercedes@test.com', initial: 'FHO', online: false },
        { email: 'raul@test.com', initial: 'RAU', online: true },
      ],
      swimlanes:LANE_DATA_LIST1
    };
    
    expect(component.listKanbas[0].select).toBeTrue();
    component.selectKanba(Kanba);
    expect(component.listKanbas[0].select).toBeFalse();
    expect(component.listKanbas[1].select).toBeTrue();
    expect(component).toBeTruthy();
  });

});
