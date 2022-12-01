import { Kanban } from '../../kanbas/model/Kanban';
import { LANE_DATA_LIST } from './../../kanbas/model/mock-kanbas-list';

import { take, map } from 'rxjs/operators';
import { KanbasService } from './../../kanbas/kanbas.service';
import { LoginService } from './../../login/login.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderComponent } from './header.component';
import { of, Observable } from 'rxjs';
import { User } from 'src/app/login/login/model/User';
import { HttpClientTestingModule } from '@angular/common/http/testing';

const loginMock = {
  logout() {
    return {
      afterClosed: () => of(true),
    };
  },
};
describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let kanbasService: KanbasService;
  let loginService: LoginService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      declarations: [HeaderComponent],

      providers: [KanbasService, { LoginService, useValue: loginMock }],
    }).compileComponents();
   
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    kanbasService = TestBed.inject(KanbasService);
    loginService = TestBed.inject(LoginService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should onLogout', () => {
    let spyOnLogout = spyOn(component, 'onLogout').and.callFake(() => true);
    let onLogout = component.onLogout();
    expect(spyOnLogout).toHaveBeenCalled();
  });

  it('should emitKankaSelect', () => {
    let users = [
      { email: 'mercedes@test.com', initial: 'FHO', online: false },
      { email: 'raul@test.com', initial: 'RAU', online: true },
    ];

    let kanba:Kanban = {
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
      swimlanes: LANE_DATA_LIST,
    };

    kanbasService.emitKankaSelect.emit(kanba);
    expect(component.users.length).toEqual(kanba.users.length);
  });
});
