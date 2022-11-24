import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginService } from '../login.service';

import { LoginComponent } from './login.component';

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let service: LoginService;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        // HttpClientTestingModule,
        FormsModule,
        ReactiveFormsModule,
    ],
      declarations: [ 
        LoginComponent,
      ],

      providers: [
        LoginService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA]
    }).compileComponents();
    
  });

  beforeEach(()=>{
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    service = TestBed.inject(LoginService);
  })

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('email should be required', () => {
    const emailField = component.form.get("email");
    emailField.setValue("");
    expect(emailField.valid).toBeFalse();
  });
  it('password should be required', () => {
    const passwdField = component.form.get("password");
    passwdField.setValue("");
    expect(passwdField.valid).toBeFalse();
  });

  it('should be an email', () => {
    const emailField = component.form.get("email");
    emailField.setValue("raul.gomez-beteta.external@capgemini.com");
    expect(emailField.valid).toBeTrue();
  });

  it('should error, its not an email', () => {
    const emailField = component.form.get("email");
    emailField.setValue("hola mundo");
    expect(emailField.valid).toBeFalse();
  });



  /*
  it('should be a valid password', () => {
    
  });

  it('should error,isnt a valid password', () => {
    
  });
  */

  it('form is valid', () => {
    const emailField = component.form.get("email");
    emailField.setValue("raul.gomez-beteta.external@capgemini.com");

    const passwdField = component.form.get("password");
    passwdField.setValue("hola");

    let spy = spyOn(service, "login").and.callThrough();
    component.onSubmit();
    expect(spy).toHaveBeenCalled();
  });
});
