import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { PermisionEditComponent } from './permision-edit.component';
import { HttpClientTestingModule } from '@angular/common/http/testing'; 


describe('PermisionEditComponent', () => {
  let component: PermisionEditComponent;
  let fixture: ComponentFixture<PermisionEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PermisionEditComponent ],
      imports: [ 
        HttpClientTestingModule,
        MatDialogModule
      ],
      providers: [
        {provide: MatDialogRef, useValue:{}},
        {provide: MAT_DIALOG_DATA, useValue:{}}]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PermisionEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
