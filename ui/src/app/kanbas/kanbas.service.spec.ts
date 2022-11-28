import { Kanba } from './model/Kanbas';
import { KanbasService } from './kanbas.service';
import {
  HttpTestingController,
  HttpClientTestingModule,
} from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';

fdescribe('kanbasService', () => {
  let service: KanbasService;
  let httpMock: HttpTestingController;

  TestBed.configureTestingModule({
    imports: [
      // HttpClientTestingModule
    ],
    providers: [KanbasService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
  });

  beforeEach(() => {
    service = TestBed.inject(KanbasService); // httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getKanbas return a list of kanbas and does a get method', () => {
    service.getKanbas().subscribe((resp: Kanba[]) => {
      expect(resp.length).toEqual(2);
    });
  });
});
