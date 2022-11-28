import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { LoginService } from './login.service';
import { LoginGuard } from './login.guard';

function fakeRouterState(url: string): RouterStateSnapshot {
  return {
    url,
  } as RouterStateSnapshot;
}

fdescribe('LoginGuard', () => {
  let guard: LoginGuard;
  let routerSpy: jasmine.SpyObj<Router>;
  let serviceStub: Partial<LoginService>;

  beforeEach(() => {
    routerSpy = jasmine.createSpyObj<Router>('Router', ['navigate']); // [1]
    TestBed.configureTestingModule({
      imports: [
          // HttpClientTestingModule
      ],
      providers: [
        LoginService,
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA]
      });
      serviceStub = TestBed.inject(LoginService);
    guard = new LoginGuard(serviceStub as LoginService, routerSpy); // [3]
  });

  const dummyRoute = {} as ActivatedRouteSnapshot;
  const fakeUrls = ['/', '/kanbas'];

  fdescribe('when the user is logged in', () => {
    beforeEach(() => {
      serviceStub.loggedIn.next(true);
    });

    it('grants access', () => {
      const isAccessGranted = guard.checkLogin(); // [2]

      expect(isAccessGranted).toBeTrue(); // [2]
    });

    fakeUrls.forEach(fakeUrl => {
      describe('and navigates to a guarded route configuration', () => {
        it('grants route access', () => {
          guard.canActivate(dummyRoute, fakeRouterState(fakeUrl)).subscribe(result => {
            expect(result).toBeTrue();
          });
        });
      });
    });
  });

  fdescribe('when the user is logged out', () => {
    beforeEach(() => {
      serviceStub.loggedIn.next(false);
    });

    it('grants access', () => {
      const isAccessGranted = guard.checkLogin(); // [2]

      expect(isAccessGranted).toBeFalse(); // [2]
    });

    fakeUrls.forEach(fakeUrl => {
      describe('and navigates to a guarded route configuration', () => {
        it('grants route access', () => {
          guard.canActivate(dummyRoute, fakeRouterState(fakeUrl)).subscribe(result => {
            expect(result).toBeFalse();
          });
        });
      });
    });
  });
});
